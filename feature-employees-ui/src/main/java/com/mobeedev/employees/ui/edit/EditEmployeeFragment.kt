package com.mobeedev.employees.ui.edit

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.UniqueOnly
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.google.android.material.snackbar.Snackbar
import com.mobeedev.commonDomain.entity.Gender
import com.mobeedev.commonUi.BaseFragment
import com.mobeedev.commonUi.simpleController
import com.mobeedev.commonUi.utils.getDayMonthYearString
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.ui.R
import com.mobeedev.employees.ui.edit.view.addressEditView
import kotlinx.android.synthetic.main.employees_edit_fragment.*
import kotlinx.android.synthetic.main.employees_edit_fragment.view.*
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.util.*


private const val EDIT_EMPLOYEE_ID = "editEmployeeId"

class EditEmployeeFragment : BaseFragment() {

    private val editEmployeeViewModel: EditEmployeeViewModel by fragmentViewModel()
    private val args: EditEmployeeFragmentArgs by navArgs()

    private val epoxyController: EpoxyController by lazy {
        simpleController(editEmployeeViewModel) { state ->
            state.addresses.forEach {
                addressEditView {
                    id(it.id)
                    addressItem(it)
                    onRemoveClicked { editEmployeeViewModel.onAddressRemoved(it) }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (args.employeeId != -1L) {
            editEmployeeViewModel.getEmployee(args.employeeId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.employees_edit_fragment, container, false).apply {
            recyclerAddress.setController(epoxyController)

            val dataAdapter =
                ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    Gender.values().map { it.toString() })
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = dataAdapter

            buttonBirthDate.text = ZonedDateTime.now().getDayMonthYearString()
            buttonBirthDate.setOnClickListener { openDatePicker() }

            buttonAddAddress.setOnClickListener {
                editEmployeeViewModel.onAddressAdded(
                    editTextAddress.text.toString()
                )
            }

            fabDone.setOnClickListener {
                if (validateData()) {
                    editEmployeeViewModel.addEmployee(
                        editTextFirstName.text.toString(),
                        editTextLastName.text.toString(),
                        Gender.valueOf(
                            spinnerGender.getItemAtPosition(spinnerGender.selectedItemPosition)
                                .toString()
                        )
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmployeeViewModel.selectSubscribe(
            owner = viewLifecycleOwner,
            prop1 = EditEmployeeState::employee,
            deliveryMode = UniqueOnly(EDIT_EMPLOYEE_ID)
        ) { employeeItem ->
            setUpEmployeeData(employeeItem)
        }
    }

    override fun invalidate() = withState(editEmployeeViewModel) { state ->
        buttonBirthDate.text = state.birthDate.getDayMonthYearString()
        epoxyController.requestModelBuild()

        if (state.navigationEventGoToHome) {
            findNavController().navigate(R.id.goToHome)
        }
    }

    private fun setUpEmployeeData(employee: EmployeeItem?) {
        employee?.apply {
            editTextFirstName.setText(firstName)
            editTextLastName.setText(lastName)

            buttonBirthDate.text = birthDate.getDayMonthYearString()

            when (gender) {
                Gender.MALE -> spinnerGender.setSelection(0)
                Gender.FEMALE -> spinnerGender.setSelection(1)
            }
        }
    }

    private fun openDatePicker() {
        Calendar.getInstance().apply {
            val day = get(Calendar.DAY_OF_MONTH)
            val month = get(Calendar.MONTH)
            val year = get(Calendar.YEAR)
            val picker = DatePickerDialog(
                requireContext(),
                OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    val birthDate = ZonedDateTime.of(
                        year,
                        monthOfYear,
                        dayOfMonth,
                        0,
                        0,
                        0,
                        0,
                        ZoneId.systemDefault()
                    )
                    editEmployeeViewModel.onBirthDateChanged(birthDate)
                },
                year,
                month,
                day
            )
            picker.show()
        }
    }

    private fun validateData(): Boolean {
        if (editTextFirstName.text.isBlank()) {
            showSnackBar(getString(R.string.first_name_blank))
            return false
        } else if (editTextLastName.text.isBlank()) {
            showSnackBar(getString(R.string.last_name_blank))
            return false
        }
        var addressesValid = true
        withState(editEmployeeViewModel) { state ->
            if (state.addresses.isEmpty()) {
                showSnackBar(getString(R.string.address_empty))
                addressesValid = false
            }
        }
        if (addressesValid.not()) return false

        return true
    }

    fun showSnackBar(message: String) =
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

}
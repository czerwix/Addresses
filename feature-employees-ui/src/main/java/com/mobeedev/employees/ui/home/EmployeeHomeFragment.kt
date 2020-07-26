package com.mobeedev.employees.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.mobeedev.commonUi.BaseFragment
import com.mobeedev.employees.ui.R
import kotlinx.android.synthetic.main.employees_home_fragment.*
import kotlinx.android.synthetic.main.employees_home_fragment.view.*

class EmployeeHomeFragment : BaseFragment() {

    private val employeeViewModel: EmployeeViewModel by activityViewModel()

    private val epoxyController = EmployeeEpoxyController(
        { employeeViewModel.removeEmployee(it) },
        {
            // TODO: 26/07/2020 navigate to add/edit screen  findNavController().navigate(R.id.goToAddEdit)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.employees_home_fragment, container, false).apply {
            recyclerEmployees.setController(epoxyController)
            fabAdd.setOnClickListener {
                // TODO: 26/07/2020 navigate to add/edit screen  findNavController().navigate(R.id.goToAddEdit)
            }
        }
    }

    override fun invalidate() = withState(employeeViewModel) { state ->
        epoxyController.setData(state.employees)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        onRecyclerViewDetached(recyclerEmployees)
    }
}
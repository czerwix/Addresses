package com.mobeedev.employees.ui.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.ui.R
import kotlinx.android.synthetic.main.employee_single_element.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EmployeeView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onRemoveClicked: ((EmployeeItem) -> Unit)? = null
        @CallbackProp set
    var onEditClicked: ((EmployeeItem) -> Unit)? = null
        @CallbackProp set
    var employeeItem: EmployeeItem? = null
        @ModelProp set

    init {
        View.inflate(context, R.layout.employee_single_element, this)
    }

    @AfterPropsSet
    fun setUp() {
        with(employeeItem!!) {
            textEmployeeName.text = "$firstName $lastName"
            textBirthDate.text = birthDate.toString()
            textGender.text = gender.toString()

            buttonEdit.setOnClickListener { onEditClicked?.invoke(this) }
            buttonRemove.setOnClickListener { onRemoveClicked?.invoke(this) }
        }
    }
}
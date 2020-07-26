package com.mobeedev.employees.ui.edit.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.mobeedev.employees.entity.AddressItem
import com.mobeedev.employees.entity.EmployeeItem
import com.mobeedev.employees.ui.R
import kotlinx.android.synthetic.main.address_edit_single_element.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddressEditView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onRemoveClicked: ((AddressItem) -> Unit)? = null
        @CallbackProp set
    var addressItem: AddressItem? = null
        @ModelProp set

    init {
        View.inflate(context, R.layout.address_edit_single_element, this)
    }

    @AfterPropsSet
    fun setUp() {
        with(addressItem!!) {
            textAddress.text = address

            buttonRemove.setOnClickListener { onRemoveClicked?.invoke(this) }
        }
    }
}
package com.mobeedev.employees.ui.home.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.mobeedev.employees.entity.AddressItem
import com.mobeedev.employees.ui.R
import kotlinx.android.synthetic.main.address_single_element.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddressView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var addressItem: AddressItem? = null
        @ModelProp set

    init {
        View.inflate(context, R.layout.address_single_element, this)
    }

    @AfterPropsSet
    fun setUp() {
        with(addressItem!!) {
            textAddress.text = address
        }
    }
}
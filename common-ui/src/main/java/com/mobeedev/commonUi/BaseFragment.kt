package com.mobeedev.commonUi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState
import com.fieldcode.commonUi.mvrx.MvRxViewModel
import timber.log.Timber

abstract class BaseFragment : BaseMvRxFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.tag(javaClass.simpleName).v("on_create")
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.tag(javaClass.simpleName).v("on_create_view")

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.tag(javaClass.simpleName).v("on_view_created")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        Timber.tag(javaClass.simpleName).v("on_resume")
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(javaClass.simpleName).v("on_pause")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.tag(javaClass.simpleName).v("on_destroy_view")
    }

    override fun onDestroy() {
        super.onDestroy()

        Timber.tag(javaClass.simpleName).v("on_destroy")
    }

    override fun onStop() {
        super.onStop()

        Timber.tag(javaClass.simpleName).v("on_Stop")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Timber.tag(javaClass.simpleName).v("on_Attach")
    }

    override fun onDetach() {
        super.onDetach()

        Timber.tag(javaClass.simpleName).v("on_Detach")
    }

    override fun onStart() {
        super.onStart()

        Timber.tag(javaClass.simpleName).v("on_Start")
    }
}

open class MvRxEpoxyController(
    val buildModelsCallback: EpoxyController.() -> Unit = {}
) : EpoxyController() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        (recyclerView.layoutManager as? LinearLayoutManager)?.let {
            it.recycleChildrenOnDetach = true
        }
    }

    override fun buildModels() {
        buildModelsCallback()
    }
}

fun <S : MvRxState, A : MvRxViewModel<S>> Fragment.simpleController(
    viewModel: A,
    buildModels: EpoxyController.(state: S) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}

fun <S1 : MvRxState, VM1 : MvRxViewModel<S1>, S2 : MvRxState, VM2 : MvRxViewModel<S2>> Fragment.simpleController(
    firstViewModel: VM1,
    secondViewModel: VM2,
    buildModels: EpoxyController.(firstState: S1, secondState: S2) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(firstViewModel, secondViewModel) { firstState, secondState ->
        buildModels(firstState, secondState)
    }
}

fun Fragment.simpleController(
    buildModels: EpoxyController.() -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController

    buildModels()
}
package com.spain_cargo.cargo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.view.ActionMode
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spain_cargo.cargo.BR

abstract class BaseFragment<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    Fragment() {

    private lateinit var viewDataBinding: T

    private var actionMode: ActionMode? = null

    // Instances that should be provided by successor of this class
    @LayoutRes
    abstract fun getLayoutId(): Int


    abstract fun getNavigator(): N

    abstract fun getViewModel(): V


    protected fun getBindingVariable(): Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getViewModel().navigator = getNavigator()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    protected fun invalidateBinding() {
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
    }

    fun setActionMode(mode: ActionMode?) {
        actionMode = mode
    }

    override fun onDestroyView() {
        super.onDestroyView()
        actionMode?.let {
            actionMode = null
            it.finish()
        }
    }


    // java Getters
    protected fun getViewDataBinding(): T = viewDataBinding

//    kotlin Getters
//    protected val viewDataBinding: T
//        get() {
//            return _viewDataBinding
//        }
}



abstract class BaseSheetFragment<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    BottomSheetDialogFragment() {

    private lateinit var viewDataBinding: T

    private var actionMode: ActionMode? = null

    // Instances that should be provided by successor of this class
    @LayoutRes
    abstract fun getLayoutId(): Int


    abstract fun getNavigator(): N

    abstract fun getViewModel(): V


    protected fun getBindingVariable(): Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getViewModel().navigator = getNavigator()


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    protected fun invalidateBinding() {
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
    }

    fun setActionMode(mode: ActionMode?) {
        actionMode = mode
    }

    override fun onDestroyView() {
        super.onDestroyView()
        actionMode?.let {
            actionMode = null
            it.finish()
        }
    }


    // Getters
    protected fun getViewDataBinding(): T = viewDataBinding

}


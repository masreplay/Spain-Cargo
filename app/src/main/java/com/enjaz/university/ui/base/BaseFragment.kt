package com.enjaz.university.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.view.ActionMode
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.enjaz.university.BR
import com.enjaz.university.di.ViewModelProviderFactory
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> : Fragment() {
    @set:Inject
    var factory: ViewModelProviderFactory? = null

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V

    private var actionMode: ActionMode? = null

    // Instances that should be provided by successor of this class
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<V>


    abstract fun getNavigator(): N

    protected fun getBindingVariable(): Int = BR.viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!::viewModel.isInitialized) {
            factory?.seedArguments(getViewModelClass(), arguments)
            viewModel = ViewModelProviders.of(this, factory).get(getViewModelClass())
        }
        viewModel.navigator = getNavigator()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.executePendingBindings()
    }

    protected fun invalidateBinding() {
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
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

    protected fun getViewModel(): V = viewModel

}

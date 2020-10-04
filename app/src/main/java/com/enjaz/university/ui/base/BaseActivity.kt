
package com.enjaz.university.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.enjaz.university.BR
import com.enjaz.university.di.ViewModelProviderFactory
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> : AppCompatActivity() {
    @set:Inject
    var factory: ViewModelProviderFactory? = null

    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V


    // Instances that should be provided by successor of this class
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelClass(): Class<V>

    abstract fun getNavigator(): N

    private fun getBindingVariable(): Int = BR.viewModel

    // Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!::viewModel.isInitialized) {
            factory?.seedArguments(getViewModelClass(), intent.extras)
            viewModel = ViewModelProvider(viewModelStore,factory as ViewModelProvider.Factory).get(getViewModelClass())
            }

        viewModel.navigator = getNavigator()

        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), viewModel)
        viewDataBinding.executePendingBindings()
    }


    // Getters
    protected fun getViewDataBinding(): T = viewDataBinding

    protected fun getViewModel(): V = viewModel

}
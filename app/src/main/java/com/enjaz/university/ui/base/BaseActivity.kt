package com.enjaz.university.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enjaz.university.BR

abstract class BaseActivity<T : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    AppCompatActivity() {


    private lateinit var viewDataBinding: T
    private lateinit var viewModel: V


    // Instances that should be provided by successor of this class
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getNavigator(): N

    private fun getBindingVariable(): Int = BR.viewModel

    // Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()


        viewModel.navigator = getNavigator()

        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
        viewDataBinding.executePendingBindings()
    }


    // Getters
    protected fun getViewDataBinding(): T = viewDataBinding


}
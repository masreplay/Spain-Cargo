package com.spain_cargo.cargo.ui.transaction

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.vvalidator.util.show
import com.spain_cargo.cargo.R
import com.spain_cargo.cargo.databinding.FragmentProfileBinding
import com.spain_cargo.cargo.ui.base.BaseFragment
import com.spain_cargo.cargo.ui.base.BaseNavigator
import com.spain_cargo.cargo.ui.login.LoginActivity
import com.spain_cargo.cargo.util.PrefsManager
import com.spain_cargo.cargo.util.copyToClipBoard
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmentProfileBinding, ITransactionInteractionListener, TransactionViewModel>(),
    ITransactionInteractionListener {

    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_transaction
    override fun getViewModel() = transactionViewModel
    override fun getNavigator() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().getTransaction()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}

interface ITransactionInteractionListener : BaseNavigator
package com.enjaz.hr.ui.requests

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentFilterBinding
import com.enjaz.hr.ui.base.BaseSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_filter.*
import java.util.*


@AndroidEntryPoint
class FilterSheet :
    BaseSheetFragment<FragmentFilterBinding, IRequestsInteractionListener, RequestsViewModel>(),
    IRequestsInteractionListener {

    private val requestsViewModel: RequestsViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.fragment_filter
    }

    override fun getViewModel(): RequestsViewModel {
        return requestsViewModel
    }

    override fun getViewModelClass(): Class<RequestsViewModel> {
        return RequestsViewModel::class.java
    }

    override fun getNavigator(): IRequestsInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_from.setOnClickListener {
            setDate(tv_from)
        }
        tv_to.setOnClickListener {
            setDate(tv_to)
        }

        btn_clear.setOnClickListener {
            for (x in 0 until g1.childCount) {
            }

        }
    }

    fun setDate(tv: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, pickedyear, monthOfYear, dayOfMonth ->
                tv.text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + pickedyear
            }, year, month, day
        )

        dpd.show()
    }


}


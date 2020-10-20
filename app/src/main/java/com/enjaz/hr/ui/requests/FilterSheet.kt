package com.enjaz.hr.ui.requests

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
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

        val newFragment = DatePickerFragment()

        tv_from.setOnClickListener {
            newFragment.show(childFragmentManager, "datePicker")
        }
        tv_to.setOnClickListener {
            newFragment.show(childFragmentManager, "datePicker")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("abdalla19988", "abdalla19988")



    }

}

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)

    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
    }
}

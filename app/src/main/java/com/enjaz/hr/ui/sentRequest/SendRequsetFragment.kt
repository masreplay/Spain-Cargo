package com.enjaz.hr.ui.sentRequest

 import android.app.DatePickerDialog
 import android.os.Bundle
import android.view.View
 import android.widget.TextView
 import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
 import com.enjaz.hr.MainActivity
 import com.enjaz.hr.R
import com.enjaz.hr.databinding.FragmentRequsetTypeBinding
 import com.enjaz.hr.databinding.FragmentSendRequestBinding
 import com.enjaz.hr.ui.attendance.AttendanceAdapter
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.ui.base.BaseSheetFragment
import dagger.hilt.android.AndroidEntryPoint
 import kotlinx.android.synthetic.main.activity_main.*
 import java.util.*
 import javax.xml.datatype.DatatypeConstants.MONTHS

@AndroidEntryPoint
class SendRequestFragment :
    BaseFragment<FragmentSendRequestBinding, ISendRequestInteractionListener, SendRequestViewModel>(),
    ISendRequestInteractionListener {

    private val sedRequestViewModel: SendRequestViewModel by viewModels()
    private val args: SendRequestFragmentArgs by navArgs()


    override fun getLayoutId(): Int {
        return R.layout.fragment_send_request
    }

    override fun getViewModel(): SendRequestViewModel {
        return sedRequestViewModel
    }

    override fun getViewModelClass(): Class<SendRequestViewModel> {
        return SendRequestViewModel::class.java
    }

    override fun getNavigator(): ISendRequestInteractionListener {
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).toolbar?.title =args.leaveType
        getViewDataBinding().dpStartDate.setOnClickListener { view ->

            showdp(getViewDataBinding().dpStartDate)
        }

        getViewDataBinding().dpEndDate.setOnClickListener { view ->

            showdp(getViewDataBinding().dpEndDate)
        }
    }

    fun showdp(tv:TextView){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            tv.setText( dayOfMonth.toString()+"/"  +monthOfYear.toString()  + "/"+year.toString())

        }, year, month, day)

        dpd.show()
    }


}


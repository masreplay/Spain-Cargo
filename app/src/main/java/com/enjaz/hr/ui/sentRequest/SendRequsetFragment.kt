package com.enjaz.hr.ui.sentRequest

import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cn.pedant.SweetAlert.SweetAlertDialog
import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.form
import com.enjaz.hr.MainActivity
import com.enjaz.hr.R
import com.enjaz.hr.databinding.CalendarDayLayoutBinding
import com.enjaz.hr.databinding.FragmentSendRequestBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.*
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthScrollListener
import com.kizitonwose.calendarview.ui.ViewContainer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*


@AndroidEntryPoint
class SendRequestFragment :
    BaseFragment<FragmentSendRequestBinding, ISendRequestInteractionListener, SendRequestViewModel>(),
    ISendRequestInteractionListener {

    private val sedRequestViewModel: SendRequestViewModel by viewModels()
    private val args: SendRequestFragmentArgs by navArgs()
    private val today = LocalDate.now()
    private var selectedDate: LocalDate? = null

    lateinit var types: ArrayList<String>
    private var startDate: LocalDate? = null
    private var endDate: LocalDate? = null

    lateinit var startDateApi: String
    lateinit var startTimeApi: String

    lateinit var endDateApi: String
    lateinit var endTimeApi: String

    var leaveTypeId: Int? = null

    lateinit var pDialog: SweetAlertDialog

    private val headerDateFormatter = DateTimeFormatter.ofPattern("EEE'\n'd MMM")


    private val startBackground: GradientDrawable by lazy {
        requireContext().getDrawableCompat(R.drawable.example_4_continuous_selected_bg_start) as GradientDrawable
    }

    private val endBackground: GradientDrawable by lazy {
        requireContext().getDrawableCompat(R.drawable.example_4_continuous_selected_bg_end) as GradientDrawable
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_send_request
    }

    override fun getViewModel(): SendRequestViewModel {
        return sedRequestViewModel
    }


    override fun getNavigator(): ISendRequestInteractionListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        types = arrayListOf()


        if (args.leaveType == "Hourly") {
            getViewModel().getLeaveTypes(true)

        } else {
            getViewModel().getLeaveTypes(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pDialog = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)

//        (activity as MainActivity).toolbar?.title = args.leaveType


        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, types
        )


        getViewModel().leaveTypesResponse.observe(requireActivity(), androidx.lifecycle.Observer {

            it.data?.items?.forEach { it ->


                types.add(it.name)

            }
            Log.d("kkkkk", types.toString())
            getViewDataBinding().spinner.adapter = adapter


        })

        getViewDataBinding().spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    long: Long
                ) {
                    leaveTypeId =
                        getViewModel().leaveTypesResponse.value?.data?.items?.get(position)?.id
                }
            }




        getViewDataBinding().exFourCalendar.post {
            val radius = ((getViewDataBinding().exFourCalendar.width / 7) / 2).toFloat()
            startBackground.setCornerRadius(topLeft = radius, bottomLeft = radius)
            endBackground.setCornerRadius(topRight = radius, bottomRight = radius)
        }


        val daysOfWeek = daysOfWeekFromLocale()


        val currentMonth = YearMonth.now()
        getViewDataBinding().exFourCalendar.setup(
            currentMonth,
            currentMonth.plusMonths(12),
            daysOfWeek.first()
        )
        getViewDataBinding().exFourCalendar.scrollToMonth(currentMonth)

        ////////////////////////////////////////////////////////////////////////////////////////////
        class SingleDayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: CalendarDay
            val binding = CalendarDayLayoutBinding.bind(view)

            init {
                binding.exFourDayText.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDate == day.date) {
                            selectedDate = null
                            getViewDataBinding().exFourCalendar.notifyDayChanged(day)
                        } else {
                            val oldDate = selectedDate
                            selectedDate = day.date
                            getViewDataBinding().exFourCalendar.notifyDateChanged(day.date)
                            oldDate?.let {
                                getViewDataBinding().exFourCalendar.notifyDateChanged(
                                    oldDate
                                )
                            }
                        }
                    }
                }
            }
        }


        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayLayoutBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH && (day.date == today || day.date.isAfter(
                            today
                        ))
                    ) {
                        val date = day.date
                        if (startDate != null) {
                            if (date < startDate || endDate != null) {
                                startDate = date
                                endDate = null
                            } else if (date != startDate) {
                                endDate = date
                            }
                        } else {
                            startDate = date
                        }
                        getViewDataBinding().exFourCalendar.notifyCalendarChanged()
                    }
                }
            }
        }

        if (args.leaveType == "Hourly") {
            form {


                input(R.id.tv_timePickerStart) {
                    matches("([0-1]?[0-9]|2[0-3]):[0-5][0-9]").description("pick a time")
                    onErrors { _, errors ->
                        val firstError: FieldError? = errors.firstOrNull()
                        getViewDataBinding().tvTimePickerStart.error = firstError?.description
                    }
                }
                input(R.id.tv_timePickerEnd) {
                    matches("([0-1]?[0-9]|2[0-3]):[0-5][0-9]").description("pick a time")
                    onErrors { _, errors ->
                        val firstError: FieldError? = errors.firstOrNull()
                        getViewDataBinding().tvTimePickerEnd.error = firstError?.description
                    }
                }

                input(R.id.tv_reason) {
                    isNotEmpty().description("Describe your leave situation")
                    onErrors { _, errors ->
                        val firstError: FieldError? = errors.firstOrNull()
                        getViewDataBinding().tvReason.error = firstError?.description
                    }
                }
                submitWith(R.id.btn_applyForLeave) {

                    leaveTypeId?.let { it1 ->
                        getViewModel().sendLeaveRequest(
                            "${startDateApi + startTimeApi}",
                            "${endDateApi + endTimeApi}",
                            getViewDataBinding().spinner.selectedItem.toString(),
                            getViewDataBinding().tvReason.text.toString(),
                            it1
                        )
                    }
                }
            }


            getViewDataBinding().lytHourly.makeVisible()
            getViewDataBinding().exFourCalendar.dayBinder =
                object : DayBinder<SingleDayViewContainer> {
                    override fun create(view: View) = SingleDayViewContainer(view)
                    override fun bind(container: SingleDayViewContainer, day: CalendarDay) {
                        container.day = day
                        val textView = container.binding.exFourDayText
                        val roundBgView = container.binding.exFourRoundBgView
                        roundBgView.makeInVisible()


                        textView.text = day.date.dayOfMonth.toString()

                        if (day.owner == DayOwner.THIS_MONTH) {
                            textView.makeVisible()
                            roundBgView.makeVisible()
                            roundBgView.setBackgroundResource(R.drawable.example_4_single_selected_bg)

                            when (day.date) {
                                today -> {
                                    startDateApi = day.date.toString()
                                    endDateApi = day.date.toString()
                                    Log.d("kkkkk", startDateApi)
                                    Log.d("kkkkk", endDateApi)

                                    textView.setTextColorRes(R.color.white)
                                    roundBgView.makeVisible()
                                    roundBgView.setBackgroundResource(R.drawable.example_4_today_bg)

                                }
                                selectedDate -> {
                                    startDateApi = day.date.toString()
                                    endDateApi = day.date.toString()
                                    Log.d("kkkkk", startDateApi)
                                    Log.d("kkkkk", endDateApi)

                                    textView.setTextColorRes(R.color.colorPrimary)
//                                textView.setBackgroundResource(R.drawable.example_4_single_selected_bg)
                                    roundBgView.makeVisible()
                                    roundBgView.setBackgroundResource(R.drawable.example_4_single_selected_bg)

                                }
                                else -> {
                                    textView.setTextColorRes(R.color.white)
                                    roundBgView.makeGone()
                                }
                            }
                        } else {
                            textView.makeInVisible()
                            roundBgView.makeGone()

                        }
                    }
                }


        } else {
            form {

                input(R.id.tv_reason) {
                    isNotEmpty().description("Describe your leave situation")
                    onErrors { _, errors ->
                        val firstError: FieldError? = errors.firstOrNull()
                        getViewDataBinding().tvReason.error = firstError?.description
                    }
                }
                submitWith(R.id.btn_applyForLeave) {

                    leaveTypeId?.let { it1 ->
                        getViewModel().sendLeaveRequest(
                            "$startDateApi",
                            "$endDateApi",
                            getViewDataBinding().spinner.selectedItem.toString(),
                            getViewDataBinding().tvReason.text.toString(),
                            it1
                        )
                    }
                }
            }



            getViewDataBinding().lytHourly.makeGone()

            getViewDataBinding().exFourCalendar.dayBinder = object : DayBinder<DayViewContainer> {
                override fun create(view: View) = DayViewContainer(view)
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    container.day = day
                    val textView = container.binding.exFourDayText
                    val roundBgView = container.binding.exFourRoundBgView

                    textView.text = null
                    textView.background = null
                    roundBgView.makeInVisible()


                    if (day.owner == DayOwner.THIS_MONTH) {
                        textView.text = day.day.toString()

                        if (day.date.isBefore(today)) {
                            textView.setTextColorRes(R.color.white)
                        } else {
                            when {
                                startDate == day.date && endDate == null -> {
                                    textView.setTextColorRes(R.color.colorPrimary)
                                    roundBgView.makeVisible()
                                    roundBgView.setBackgroundResource(R.drawable.example_4_single_selected_bg)
                                }
                                day.date == startDate -> {
                                    startDateApi = day.date.toString()
                                    Log.d("kkkkk", startDateApi)

                                    textView.setTextColorRes(R.color.colorPrimary)
                                    textView.background = startBackground

                                }
                                startDate != null && endDate != null && (day.date > startDate && day.date < endDate) -> {
                                    textView.setTextColorRes(R.color.white)
                                    textView.setBackgroundResource(R.drawable.example_4_continuous_selected_bg_middle)
                                }
                                day.date == endDate -> {
                                    endDateApi = day.date.toString()
                                    Log.d("kkkkk", endDateApi)

                                    textView.setTextColorRes(R.color.colorPrimary)
                                    textView.background = endBackground
                                }
                                day.date == today -> {
                                    startDateApi = day.date.toString()
                                    endDateApi = day.date.toString()
                                    Log.d("kkkkk", startDateApi)
                                    Log.d("kkkkk", endDateApi)

                                    textView.setTextColorRes(R.color.white)
                                    roundBgView.makeVisible()
                                    roundBgView.setBackgroundResource(R.drawable.example_4_today_bg)
                                }
                                else -> textView.setTextColorRes(R.color.white)
                            }
                        }
                    } else {

                        // This part is to make the coloured selection background continuous
                        // on the blank in and out dates across various months and also on dates(months)
                        // between the start and end dates if the selection spans across multiple months.

                        val startDate = startDate
                        val endDate = endDate
                        if (startDate != null && endDate != null) {
                            // Mimic selection of inDates that are less than the startDate.
                            // Example: When 26 Feb 2019 is startDate and 5 Mar 2019 is endDate,
                            // this makes the inDates in Mar 2019 for 24 & 25 Feb 2019 look selected.
                            if ((day.owner == DayOwner.PREVIOUS_MONTH &&
                                        startDate.monthValue == day.date.monthValue &&
                                        endDate.monthValue != day.date.monthValue) ||
                                // Mimic selection of outDates that are greater than the endDate.
                                // Example: When 25 Apr 2019 is startDate and 2 May 2019 is endDate,
                                // this makes the outDates in Apr 2019 for 3 & 4 May 2019 look selected.
                                (day.owner == DayOwner.NEXT_MONTH &&
                                        startDate.monthValue != day.date.monthValue &&
                                        endDate.monthValue == day.date.monthValue) ||

                                // Mimic selection of in and out dates of intermediate
                                // months if the selection spans across multiple months.
                                (startDate < day.date && endDate > day.date &&
                                        startDate.monthValue != day.date.monthValue &&
                                        endDate.monthValue != day.date.monthValue)
                            ) {
                                textView.setBackgroundResource(R.drawable.example_4_continuous_selected_bg_middle)
                            }
                        }
                    }
                }
            }
        }



        getViewDataBinding().exFourCalendar.monthScrollListener = object : MonthScrollListener {
            override fun invoke(p1: CalendarMonth) {
                val monthYearTitle =
                    "${p1.yearMonth.month.name.toLowerCase().capitalize()} ${p1.year}"

                (activity as MainActivity).toolbar?.title = monthYearTitle

            }

        }


    }

    override fun dialogTimePickerLightStart() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            timePicker.setBackgroundResource(R.color.colorPrimary)
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            getViewDataBinding().tvTimePickerStart.setText(SimpleDateFormat("HH:mm").format(cal.time))
            startTimeApi = "T${SimpleDateFormat("HH:mm").format(cal.time)}"
            Log.d("kkkkk", startTimeApi)
        }
        TimePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()

    }


    override fun dialogTimePickerLightEnd() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            getViewDataBinding().tvTimePickerEnd.setText(SimpleDateFormat("HH:mm").format(cal.time))
            endTimeApi = "T${SimpleDateFormat("HH:mm").format(cal.time)}"
            Log.d("kkkkk", endTimeApi)
        }
        TimePickerDialog(
            requireContext(),
            R.style.DialogTheme,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            false
        ).show()

    }

    override fun onSendingRequestSuccess() {
        Handler().postDelayed({
            pDialog.hide()
            SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(getString(R.string.done))
                .setContentText(getString(R.string.request_sent))
                .show()
            Handler().postDelayed({
                requireActivity().onBackPressed()
            }, 1000)
        }, 1000)

    }

    override fun onSendingRequestError(message: String) {
        Handler().postDelayed({
            pDialog.hide()
            SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText(getString(R.string.ops))
                .setContentText(message)
                .show()
        }, 1000)
    }

    override fun onSendingRequest() {
        pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        pDialog.titleText = getString(R.string.sending_request)
        pDialog.setCancelable(false)
        pDialog.show()
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {

        snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())

    }
}

interface ISendRequestInteractionListener : BaseNavigator {
    fun dialogTimePickerLightStart()
    fun dialogTimePickerLightEnd()

    fun onSendingRequestSuccess()
    fun onSendingRequestError(message: String)
    fun onSendingRequest()

    fun showSnack(string: String, color: String, drawable: Int?)

}


package com.enjaz.hr.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.futured.donut.DonutSection
import com.enjaz.hr.R
import com.enjaz.hr.databinding.FramgnetHomeBinding
import com.enjaz.hr.ui.base.BaseFragment
import com.enjaz.hr.ui.base.BaseNavigator
import com.enjaz.hr.util.calendar.HorizontalCalendar
import com.enjaz.hr.util.calendar.utils.HorizontalCalendarListener
import com.enjaz.hr.util.snackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.framgnet_home.*
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FramgnetHomeBinding, IHomeInteractionListener, HomeViewModel>(),
    IHomeInteractionListener {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var usersAdapter: UsersAdapter
    lateinit var employeeAdapter: EmployeesAdapter


    override fun getLayoutId(): Int {
        return R.layout.framgnet_home
    }

    override fun getViewModel(): HomeViewModel {
        return homeViewModel
    }


    override fun getNavigator(): IHomeInteractionListener {
        return this
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().status.constraintLoading.setBackgroundColor(Color.WHITE)
        getViewDataBinding().status.constraintError.setBackgroundColor(Color.WHITE)


        val calendar = Calendar.getInstance()

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -12)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 12)

        val horizontalCalendar: HorizontalCalendar =
            HorizontalCalendar.Builder(view, R.id.rv_date)
                .range(startDate, endDate)
                .mode(HorizontalCalendar.Mode.MONTHS)
                .datesNumberOnScreen(5)
                .configure()
                .textSize(12.0F, 16.0F, 12.0F)
                .formatMiddleText("yyyy")
                .formatTopText("LLL")
                .showTopText(true)
                .showBottomText(false)
                .end()
                .defaultSelectedDate(calendar)
                .build()



        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar?, position: Int) { //do something

                getViewModel().getHomeData(
                    (horizontalCalendar.selectedDate.time.month + 1),
                    horizontalCalendar.selectedDate.get(Calendar.YEAR)
                )


            }
        }

        getViewDataBinding().rv.apply {
            adapter = usersAdapter
        }

        getViewDataBinding().rv2.apply {
            adapter = employeeAdapter
        }


        getViewModel().homeResponse.observe(requireActivity(), Observer { response ->
            response.data?.let {


                val section1 = DonutSection(
                    name = "section_1",
                    color = ContextCompat.getColor(requireActivity(), R.color.green),
                    amount = it.vacation.toFloat()
                )


                val section2 = DonutSection(
                    name = "section_2",
                    color = ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
                    amount = it.present.toFloat()
                )

                val section3 = DonutSection(
                    name = "section_3",
                    color = ContextCompat.getColor(requireActivity(), R.color.orange),
                    amount = it.delay.toFloat()
                )

                val section4 = DonutSection(
                    name = "section_4",
                    color = ContextCompat.getColor(requireActivity(), R.color.red),
                    amount = it.absent.toFloat()
                )

                donut_attendance?.cap = it.totalWorkdays.toFloat()
                donut_attendance?.submitData(listOf(section1, section2, section3, section4))
            }

        })

    }


    override fun showTeam() {
        findNavController().navigate(R.id.teamFragment)
    }

    override fun showEmployees() {
        findNavController().navigate(R.id.teamFragment)
    }

    override fun showSnack(string: String, color: String, drawable: Int?) {
        snackBar(string, drawable, color, getViewDataBinding().parent, requireContext())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersAdapter = UsersAdapter(requireContext(), mutableListOf())
        employeeAdapter = EmployeesAdapter(requireContext(), mutableListOf())
        val calendar = Calendar.getInstance()
        getViewModel().getHomeData((calendar.get(Calendar.MONTH) + 1), calendar.get(Calendar.YEAR))
    }
}

interface IHomeInteractionListener : BaseNavigator {
    fun showTeam()
    fun showEmployees()
    fun showSnack(string: String, color: String, drawable: Int?)

}

package com.enjaz.hr.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.enjaz.hr.HRMApp
import com.enjaz.hr.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.*

fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, textId, duration).show() }

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

fun View.snackbar(snackbarText: String,@ColorInt colorInt: Int) {
    Snackbar.make(this, snackbarText, Snackbar.LENGTH_LONG).withColor(colorInt).show()

}

fun String.amPm():String {

    val time =this.toString()

    Log.d("kkkkk",time.substringBefore(":"))
    return if (time.substringBefore(":").toInt()<12){
       this +" "+ HRMApp.applicationContext().getString(R.string.am)
    }else this + " "+ HRMApp.applicationContext().getString(R.string.pm)
}


fun String.toDate(): String {


    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val apiDate = parser.parse(this)

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    val date = sdf.format(apiDate)
    return date


}

fun String.toDay(): String {


    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val apiDate = parser.parse(this)

    val sdf = SimpleDateFormat("EEE", Locale.ENGLISH)

    val date = sdf.format(apiDate)
    return date


}


fun snackBar(s: String, drawable: Int?, color: String, view: View, context: Context) {
    val snackbar =

        Snackbar.make(
            view,
            "",
            Snackbar.LENGTH_SHORT
        )

    var layoutInflater: LayoutInflater = LayoutInflater.from(context)
    val custom_view: View =
        layoutInflater.inflate(R.layout.snackbar_icon_text, null)
    snackbar.view.setBackgroundColor(Color.TRANSPARENT)
    val snackBarView: Snackbar.SnackbarLayout =
        snackbar.view as Snackbar.SnackbarLayout
    snackBarView.setPadding(0, 0, 0, 0)
    (custom_view.findViewById<View>(R.id.message) as TextView).text = s

    if (drawable != null) {
        (custom_view.findViewById<View>(R.id.icon) as ImageView).setImageResource(
            drawable
        )
    }

    custom_view.findViewById<View>(R.id.parentview).setBackgroundColor(
        Color.parseColor(
            color
        )
    )
    snackBarView.addView(custom_view, 0)
    snackbar.show()
}
    fun View.makeVisible() {
        visibility = View.VISIBLE
    }

    fun View.makeInVisible() {
        visibility = View.INVISIBLE
    }

    fun View.makeGone() {
        visibility = View.GONE
    }

    fun dpToPx(dp: Int, context: Context): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()

    internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return context.layoutInflater.inflate(layoutRes, this, attachToRoot)
    }

    internal val android.content.Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

    internal val android.content.Context.inputMethodManager
    get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    internal inline fun Boolean?.orFalse(): Boolean = this ?: false

    internal fun Context.getDrawableCompat(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

    internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

    internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))

    fun daysOfWeekFromLocale(): Array<DayOfWeek> {
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        var daysOfWeek = DayOfWeek.values()
        // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
        // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
        if (firstDayOfWeek != DayOfWeek.MONDAY) {
            val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
            val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
            daysOfWeek = rhs + lhs
        }
        return daysOfWeek
    }

    fun GradientDrawable.setCornerRadius(
        topLeft: Float = 0F,
        topRight: Float = 0F,
        bottomRight: Float = 0F,
        bottomLeft: Float = 0F
    ) {
        cornerRadii = arrayOf(
            topLeft, topLeft,
            topRight, topRight,
            bottomRight, bottomRight,
            bottomLeft, bottomLeft
        ).toFloatArray()
    }


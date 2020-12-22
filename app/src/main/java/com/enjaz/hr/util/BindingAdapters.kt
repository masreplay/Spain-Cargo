package com.enjaz.hr.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.vvalidator.util.hide
import com.enjaz.hr.R
import com.enjaz.hr.ui.base.BaseDataItemsAdapter
import com.facebook.drawee.view.SimpleDraweeView
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("bind:overlayResource")
fun loadImage(view: SimpleDraweeView, resource: Int?) {
    val colorDrawable = ColorDrawable(view.context.resources.getColor(resource!!))
    view.hierarchy.setOverlayImage(colorDrawable)
}

@BindingAdapter("bind:imageUrl")
fun loadImage(view: SimpleDraweeView, imageUrl: String?) {
    view.setImageURI(imageUrl)
}


@BindingAdapter("timeFormat")
fun setTime(view: TextView, time: Int) {
    view.text = "$time:00"
}

@BindingAdapter("progressColors")
fun setProgressColors(progressBar: ProgressBar, position: Int) {
    when(position){

        0-> {
            progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor("#a8d8ea"))
            progressBar.progressBackgroundTintList=ColorStateList.valueOf(Color.parseColor("#a8d8ea"))
        }

        1->{
            progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor("#aa96da"))
            progressBar.progressBackgroundTintList=ColorStateList.valueOf(Color.parseColor("#aa96da"))
        }
        2->{
            progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor("#fcbad3"))
            progressBar.progressBackgroundTintList=ColorStateList.valueOf(Color.parseColor("#fcbad3"))
        }
        3->{
            progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor("#EAC680"))
            progressBar.progressBackgroundTintList=ColorStateList.valueOf(Color.parseColor("#EAC680"))
        }else->{
        progressBar.progressTintList= ColorStateList.valueOf(Color.parseColor("#a8d8ea"))
        progressBar.progressBackgroundTintList=ColorStateList.valueOf(Color.parseColor("#a8d8ea"))
    }

    }
}



@BindingAdapter("balance")
fun setClickableIf(view: TextView, string: String) {
    when(string){
        "Vacation" -> view.text="2 days left"
        "Hourly" -> view.text="23 hours left"
        else -> view.hide()
    }
}


@BindingAdapter("ivType")
fun setIv(view: ImageView, string: String) {


    when (string) {

        "Vacation" -> view.setImageResource(R.drawable.ic_vacation)
        "Hourly" -> view.setImageResource(R.drawable.ic_time)
        "Overtime" -> view.setImageResource(R.drawable.ic_overtime)
        "Miss punch" -> view.setImageResource(R.drawable.ic_fingerprint)


    }
}


@BindingAdapter("monthFormat")
fun setMonth(view: TextView, pattern: String) {

    val month = SimpleDateFormat(pattern)


    val currentMonth = month.format(Date())
    view.text = currentMonth

}

@BindingAdapter("yearFormat")
fun setYear(view: TextView, pattern: String) {
    val year = SimpleDateFormat(pattern)

    val currentYear: String = year.format(Date())
    view.text = currentYear

}

@BindingAdapter("itemBackground")
fun setTypeBackground(view: TextView, color: String) {

    view.setBackgroundColor(Color.parseColor(color))

}

@BindingAdapter("randomColorBackground")
fun setBackgroundColors(view: View, colors: IntArray) {
    val randomAndroidColor: Int = colors[Random().nextInt(colors.size)]
    view.setBackgroundColor(randomAndroidColor)

}


@BindingAdapter("bind:total", "bind:value")
fun setPercentage(view: TextView, total: Int, value: Int) {

    val percentage = (value.toFloat() / total.toFloat() * 100).toInt().toString()


    if (percentage.toInt() > 50) {
        view.setTextColor(Color.parseColor("#10c830"))
    } else if (percentage.toInt() < 50) {

        view.setTextColor(Color.parseColor("#f10e0b"))

    } else view.setTextColor(Color.parseColor("#ec7521"))



    view.text = percentage + "%"
}

@BindingAdapter("bind:degreeFrom", "bind:degree")
fun setMarkBackground(view: TextView, degreeFrom: Int, degree: Int) {


    val diff: Int = degreeFrom - degree

    if (diff < degreeFrom / 2) {
        view.setTextColor(Color.parseColor("#10c830"))
    } else if (diff > degreeFrom / 2) {
        view.setTextColor(Color.parseColor("#f10e0b"))

    } else view.setTextColor(Color.parseColor("#ec7521"))


}


@BindingAdapter("android:layout_width")
fun setLayoutWidth(view: View, width: Float) {
    val layoutParams = view.layoutParams
    layoutParams.width = width.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("android:layout_height")
fun setLayoutHeight(view: View, height: Float) {
    val layoutParams = view.layoutParams
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

@BindingAdapter("android:layout_marginEnd")
fun setMarginEnd(view: View, margin: Float) {
    val params = view.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.marginEnd = margin.toInt()
        params.marginEnd = margin.toInt()
        view.layoutParams = params
    }
}


@BindingAdapter(value = ["items"])
fun <T> setItems(view: RecyclerView, items: MutableList<T>?) {
    Log.d("ausamah", " set items called ")
    items?.let {
        (view.adapter as? BaseDataItemsAdapter<T>)?.apply {
            setItems(it)
            Log.d("ausamah", "size " + it.size)
        }
    }
}

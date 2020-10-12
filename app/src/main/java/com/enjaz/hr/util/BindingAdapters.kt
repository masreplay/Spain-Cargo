package com.enjaz.hr.util

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

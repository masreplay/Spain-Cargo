package com.spain_cargo.cargo.util

import android.net.Uri
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.github.marlonlom.utilities.timeago.TimeAgoMessages
import com.spain_cargo.cargo.ui.base.BaseDataItemsAdapter
import java.util.*


@BindingAdapter("imageUrl")
fun loadImage(view: SimpleDraweeView, imageUrl: String?) {
    view.setImageURI(imageUrl)
}

@BindingAdapter("timeAgo")
fun timeAgo(view: TextView, date: Date?) {

    val locale = Locale.forLanguageTag("ar")
    val messages = TimeAgoMessages.Builder()
        .withLocale(locale).build()
    date?.let {
        view.text = TimeAgo.using(date.time, messages)
    }
}


@BindingAdapter(value = ["items"])
fun <T> setItems(view: RecyclerView, items: MutableList<T>?) {
    items?.let {
        (view.adapter as? BaseDataItemsAdapter<T>)?.apply {
            setItems(it)
        }
    }
}

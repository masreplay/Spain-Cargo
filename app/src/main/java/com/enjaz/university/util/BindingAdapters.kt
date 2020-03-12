package com.enjaz.university.util

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enjaz.university.ui.base.BaseDataItemsAdapter
import com.facebook.drawee.view.SimpleDraweeView


@BindingAdapter("bind:overlayResource")
	fun loadImage(view: SimpleDraweeView, resource: Int?) {
		val colorDrawable = ColorDrawable(view.context.resources.getColor(resource!!))
		view.hierarchy.setOverlayImage(colorDrawable)
	}

	@BindingAdapter("bind:imageUrl")
	fun loadImage(view: SimpleDraweeView, imageUrl: String?) {
		view.setImageURI(imageUrl)
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
fun <T> setItems(view: RecyclerView, items:MutableList<T>?) {
	Log.d("ausamah"," set items called " )
	items?.let {
		(view.adapter as? BaseDataItemsAdapter<T>)?.apply {
			setItems(it)
			Log.d("ausamah","size "+it.size )
		}
	}}
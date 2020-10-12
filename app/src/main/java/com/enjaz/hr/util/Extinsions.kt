package com.enjaz.hr.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) = this?.let { Toast.makeText(it, textId, duration).show() }

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

fun View.snackbar(snackbarText: String,@ColorInt colorInt: Int) {
    Snackbar.make(this, snackbarText, Snackbar.LENGTH_LONG).withColor(colorInt).show()
}
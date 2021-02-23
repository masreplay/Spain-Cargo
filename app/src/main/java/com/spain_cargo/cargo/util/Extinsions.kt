package com.spain_cargo.cargo.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.spain_cargo.cargo.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, textId, duration).show() }

fun View.snackbar(snackbarText: String) {
    Snackbar.make(this, snackbarText, Snackbar.LENGTH_LONG).show()
}

fun Any?.print(tag: String = "abdalla1997") {
    this?.also {
        Log.d(tag, it.toString())
    }
}

fun Int?.toFormat(): String {
    return DecimalFormat("#,###", DecimalFormatSymbols.getInstance(Locale.US)).format(this) + " د.ع"
}

fun Fragment.copyToClipBoard(body: String?, label: String = "label") {
    val cm: ClipboardManager =
        this.requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(
        ClipData.newPlainText(
            label, body ?: ""
        )
    )
    requireContext().toast(R.string.msg_copy_to_clipboard)
}

fun Fragment.snackbar(
    message: String?,
    action: (() -> Unit)? = null,
    actionTitle: String? = null
) {
    Snackbar.make(
        this.requireView(),
        message ?: "",
        Snackbar.LENGTH_LONG
    ).also { snackbar ->
        snackbar.setAction(actionTitle ?: requireContext().getString(R.string.option_ok)) {
            action ?: snackbar.dismiss()
        }
    }.show()
}

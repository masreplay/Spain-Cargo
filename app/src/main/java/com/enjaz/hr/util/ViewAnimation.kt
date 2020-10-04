package com.enjaz.hr.util

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

class ViewAnimation {

    fun expand(v: View) {
        val a = expandAction(v)
        v.startAnimation(a)
    }

    private fun expandAction(v: View): Animation {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targtetHeight = v.measuredHeight
        v.layoutParams.height = 0
        v.visibility = View.VISIBLE
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                v.layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targtetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.setDuration(
            (targtetHeight / v.context.resources.displayMetrics.density).toLong()

        )
        v.startAnimation(a)
        return a
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight
        val a: Animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation
            ) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.setDuration(
            (initialHeight / v.context.resources.displayMetrics.density).toLong()

        )
        v.startAnimation(a)
    }

    fun toggleArrow(view: View): Boolean {
        return if (view.rotation == 0f) {
            view.animate().setDuration(200).rotation(180f)
            true
        } else {
            view.animate().setDuration(200).rotation(0f)
            false
        }
    }

    fun toggleArrow(show: Boolean, view: View): Boolean {
        return toggleArrow(show, view, true)
    }

    fun toggleArrow(
        show: Boolean,
        view: View,
        delay: Boolean
    ): Boolean {
        return if (show) {
            view.animate().setDuration(if (delay) 200 else 0.toLong()).rotation(180f)
            true
        } else {
            view.animate().setDuration(if (delay) 200 else 0.toLong()).rotation(0f)
            false
        }
    }

}
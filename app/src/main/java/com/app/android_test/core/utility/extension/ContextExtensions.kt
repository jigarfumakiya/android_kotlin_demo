package com.app.android_test.core.utility.extension

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import kotlin.math.roundToInt

val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Float.convertPixelsToDp(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val dp: Float = this / (metrics.densityDpi / 160f)
    return dp.roundToInt()
}
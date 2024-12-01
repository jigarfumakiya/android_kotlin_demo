package com.app.android_test.core.app

import android.view.View
import androidx.core.content.ContextCompat
import com.app.android_test.R
import com.google.android.material.snackbar.Snackbar


/**
 * @Author: Jigar Fumakiya
 * @Date: 01/05/23
 * Bits
 */

object SnackbarUtils {
    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(view.context, R.color.black))
            .setTextColor(ContextCompat.getColor(view.context, R.color.white))
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)
            .show()
    }

}
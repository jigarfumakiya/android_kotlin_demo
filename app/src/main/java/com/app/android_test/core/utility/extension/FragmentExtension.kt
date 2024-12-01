package com.app.android_test.core.utility.extension

import androidx.fragment.app.Fragment
import com.app.android_test.R
import com.app.android_test.core.utility.dialog.ErrorDialog


/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */


fun Fragment.showErrorDialog(message: String?, title: String? = null) {
    val errorDialog = ErrorDialog(
        title ?: getString(R.string.error),
        message ?: getString(R.string.something_went_wrong)
    )
    if (!errorDialog.isVisible) {
        errorDialog.show(childFragmentManager, ErrorDialog::class.simpleName)
    }
}
 

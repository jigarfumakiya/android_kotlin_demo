package com.app.android_test.core.utility.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.app.android_test.R
import com.app.android_test.databinding.DialogErrorBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */


class ErrorDialog(
    private val title: String,
    private val message: String,
) : DialogFragment() {

    override fun getTheme() = R.style.RoundedCornersDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogErrorBinding.inflate(requireActivity().layoutInflater).apply {
            dialogErrorTitle.text = title
            dialogErrorMessage.text = message
            dialogErrorOkButton.setOnClickListener { dismiss() }
        }
        return MaterialAlertDialogBuilder(requireActivity(), R.style.MaterialDialogRounded).apply {
            setView(binding.root)
        }.create()

    }
}
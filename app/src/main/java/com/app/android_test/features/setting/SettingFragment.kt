package com.app.android_test.features.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.android_test.R
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.databinding.FragmentSettingBinding

class SettingFragment : Fragment(R.layout.fragment_setting) {
    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }


    private fun setupViews() {
        with(binding) {
            logoutTextView.setOnClickListener {
                viewModel.logout()
            }
        }

    }
}
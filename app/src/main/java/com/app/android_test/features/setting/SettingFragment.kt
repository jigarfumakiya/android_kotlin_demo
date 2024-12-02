package com.app.android_test.features.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.app.android_test.R
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.core.utility.extension.showErrorDialog
import com.app.android_test.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {
    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }


    private fun setupViews() {
        with(binding) {
            logoutButton.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logoutFlow.collect {
                    when (it) {
                        is com.app.android_test.core.app.Result.Loading -> {

                        }

                        is com.app.android_test.core.app.Result.Success -> {
                            findNavController().navigate(R.id.action_nav_setting_to_welcomeFragment)
                        }

                        is com.app.android_test.core.app.Result.Error -> {
                            showErrorDialog(message = it.exception.message)
                        }
                    }
                }
            }
        }
    }
}
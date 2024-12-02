package com.app.android_test.features.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.app.android_test.R
import com.app.android_test.core.app.SnackbarUtils
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.core.utility.extension.showErrorDialog
import com.app.android_test.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    val binding by viewBinding(FragmentWelcomeBinding::bind)
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObserver()
    }


    private fun setupViews() {
        with(binding) {
            loginButton.setOnClickListener {
                val name = nameEditText.text.toString()
                if (name.isEmpty()) {
                    SnackbarUtils.showSnackbar(binding.root, getString(R.string.name))
                    return@setOnClickListener
                }
                saveUserDetails(name)
            }
        }
    }

    private fun saveUserDetails(name: String) {
        viewModel.saveUser(name)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signupFlow.collect {
                    when (it) {
                        is com.app.android_test.core.app.Result.Loading -> {

                        }

                        is com.app.android_test.core.app.Result.Success -> {
                            findNavController().navigate(R.id.action_welcomeFragment_to_nav_home)
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
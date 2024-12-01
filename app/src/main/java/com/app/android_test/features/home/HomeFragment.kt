package com.app.android_test.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.app.android_test.R
import com.app.android_test.core.utility.binding.viewBinding
import com.app.android_test.core.utility.extension.showErrorDialog
import com.app.android_test.databinding.FragmentHomeBinding
import com.app.android_test.domain.model.ArticleDomain
import com.app.android_test.features.home.adapters.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy {
        NewsAdapter(this::newsTap)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
    }

    private fun initViews() {
        with(binding) {
            storiesRecyclerview.adapter = adapter
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsFlow.collect {
                    when (it) {
                        is com.app.android_test.core.app.Result.Loading -> {
                            showSkeleton()
                        }

                        is com.app.android_test.core.app.Result.Success -> {
                            hideSkeleton()
                            loadData(it.data)
                        }

                        is com.app.android_test.core.app.Result.Error -> {
                            showErrorDialog(message = it.exception.message)
                        }
                    }
                }
            }
        }
    }

    private fun hideSkeleton() {
        binding.progressCircular.isEnabled = false
    }

    private fun showSkeleton() {
        binding.progressCircular.isEnabled = true
    }

    private fun loadData(data: List<ArticleDomain>) {
        adapter.submitList(data)
    }

    private fun newsTap(airtcle: ArticleDomain): Unit {

    }
}
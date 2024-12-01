package com.app.android_test.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.android_test.core.app.Result
import com.app.android_test.domain.model.ArticleDomain
import com.app.android_test.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
) : ViewModel() {
    private val _news = Channel<Result<List<ArticleDomain>>>(capacity = Channel.CONFLATED)
    val newsFlow = _news.receiveAsFlow()

    init {
        getNewsHeadLine();
    }

    fun getNewsHeadLine() {
        _news.trySend(Result.Loading)
        viewModelScope.launch {
            val currency = newsUseCase.getTopHeadline()
            _news.trySend(currency)
        }
    }
}
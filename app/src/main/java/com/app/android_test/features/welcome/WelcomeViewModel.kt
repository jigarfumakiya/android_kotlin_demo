package com.app.android_test.features.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.android_test.core.app.Result
import com.app.android_test.domain.usecase.AuthUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authUsecase: AuthUsecase
) : ViewModel() {
    private val signup =
        Channel<Result<Unit>>(capacity = Channel.CONFLATED)
    val signupFlow = signup.receiveAsFlow()

    fun saveUser(name: String) {
        signup.trySend(Result.Loading)
        viewModelScope.launch {
            val result = authUsecase.saveUser(name)
            signup.trySend(result)
        }
    }
}
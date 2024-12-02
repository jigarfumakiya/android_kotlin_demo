package com.app.android_test.features.setting

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
class SettingViewModel @Inject constructor(
    private val authUsecase: AuthUsecase
) : ViewModel() {
    private val _logout = Channel<Result<Unit>>(capacity = Channel.CONFLATED)
    val logoutFlow = _logout.receiveAsFlow()
    fun logout() {
        _logout.trySend(Result.Loading)
        viewModelScope.launch {
            val currency = authUsecase.logout()
            _logout.trySend(currency)
        }

    }
}
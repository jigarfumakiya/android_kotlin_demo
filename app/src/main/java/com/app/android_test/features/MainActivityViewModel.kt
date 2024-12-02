package com.app.android_test.features

import androidx.lifecycle.ViewModel
import com.app.android_test.domain.usecase.AuthUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authUsecase: AuthUsecase
) : ViewModel() {

    suspend fun userLogged(): Boolean {
        return authUsecase.userLogged()
    }

}

 

package com.app.android_test.data.repository.auth

import com.app.android_test.domain.model.UserDomain

/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */

interface AuthRepository {
    suspend fun saveUser(name: String)
    suspend fun getUser(): UserDomain
    suspend fun userLogged(): Boolean

    suspend fun logout()
}
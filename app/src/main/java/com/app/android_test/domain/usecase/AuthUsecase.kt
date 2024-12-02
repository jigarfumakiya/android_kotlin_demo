package com.app.android_test.domain.usecase

import com.app.android_test.core.app.Result
import com.app.android_test.core.utility.exception.getException
import com.app.android_test.data.repository.auth.AuthRepository
import com.app.android_test.domain.model.UserDomain
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */
@ViewModelScoped
class AuthUsecase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun saveUser(name: String): Result<Unit> {
        return try {
            val result = authRepository.saveUser(name)
            Result.Success(result)
        } catch (ioException: IOException) {
            Result.Error(ioException)
        } catch (exception: HttpException) {
            Result.Error(exception.getException())
        }
    }

    suspend fun getUser(name: String): Result<UserDomain> {
        return try {
            val result = authRepository.getUser()
            Result.Success(result)
        } catch (ioException: IOException) {
            Result.Error(ioException)
        } catch (exception: HttpException) {
            Result.Error(exception.getException())
        }
    }

    suspend fun userLogged(): Boolean {
        return authRepository.userLogged()
    }

    suspend fun logout():Result<Unit> {
        return try {
            val result = authRepository.logout()
            Result.Success(result)
        } catch (ioException: IOException) {
            Result.Error(ioException)
        } catch (exception: HttpException) {
            Result.Error(exception.getException())
        }
    }

}
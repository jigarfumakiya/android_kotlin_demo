package com.app.android_test.data.repository.auth

import com.app.android_test.core.di.AppModule
import com.app.android_test.data.db.UserEntity
import com.app.android_test.data.db.dao.UserDao
import com.app.android_test.data.mappers.toDomain
import com.app.android_test.domain.model.UserDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    @AppModule.IOScope
    private val ioScope: CoroutineScope,
) : AuthRepository {
    override suspend fun saveUser(name: String) {
        userDao.saveUser(UserEntity(0, name))
    }

    override suspend fun getUser(): UserDomain {
        val domain = withContext(ioScope.coroutineContext) {
            userDao.getUser().first()
        }
        return domain.first().toDomain()
    }

    override suspend fun userLogged(): Boolean {
        return userDao.userLogged()
    }
}
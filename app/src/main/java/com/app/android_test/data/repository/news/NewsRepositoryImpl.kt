package com.app.android_test.data.repository.news

import com.app.android_test.core.di.AppModule
import com.app.android_test.data.mappers.toDomain
import com.app.android_test.data.network.Api
import com.app.android_test.domain.model.NewsDomain
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: Api,
    @AppModule.IOScope
    private val ioScope: CoroutineScope,
) : NewsRepository {
    override suspend fun getNewsHeadlines(): NewsDomain {
        return api.getTopHeadlines().toDomain()
    }
}
 

 

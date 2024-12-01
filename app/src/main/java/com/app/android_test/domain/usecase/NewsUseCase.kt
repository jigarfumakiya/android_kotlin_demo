package com.app.android_test.domain.usecase

import com.app.android_test.core.app.Result
import com.app.android_test.core.utility.exception.getException
import com.app.android_test.data.repository.news.NewsRepository
import com.app.android_test.domain.model.ArticleDomain
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

@ViewModelScoped
class NewsUseCase @Inject constructor(
   private  val newsRepository: NewsRepository
){

   suspend fun getTopHeadline():Result<List<ArticleDomain>>{
       return try {
            val result = newsRepository.getNewsHeadlines()
            Result.Success(result.articles)
        }catch (ioException: IOException) {
            Result.Error(ioException)
        } catch (exception: HttpException) {
            Result.Error(exception.getException())
        }
    }

}

 

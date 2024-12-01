package com.app.android_test.data.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.app.android_test.core.utility.DoesNetworkHaveInternet
import com.app.android_test.core.utility.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

@Singleton
class AuthenticationInterceptor @Inject constructor(
    private val application: Application
) :
    Interceptor {
    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternetAccess()) {
            throw NoInternetException("No Internet found")
        }
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader("Authorization", "f34a02512f0c48ee92aabc8efb5e230c")
        return chain.proceed(requestBuilder.build())
    }


    private fun hasInternetAccess(): Boolean {
        val cm =
            application.applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = cm.activeNetwork
        return if (network != null) {
            // if user does not wifi connected or internet then
            val hasInternet = DoesNetworkHaveInternet.execute(network.socketFactory)
            hasInternet
        } else {
            return false
        }
    }


}
 

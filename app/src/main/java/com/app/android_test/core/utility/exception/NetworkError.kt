package com.app.android_test.core.utility.exception


import android.util.Log
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okio.Buffer
import okio.GzipSource
import retrofit2.HttpException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

data class NetworkErrorException(
    val code: Any,
    val error: String,
) : Exception(
    error
)

fun HttpException.getException(): Exception {
    val errorMessage = response()?.let { response ->
        response.errorBody()?.source()?.let { source ->
            source.request(Long.MAX_VALUE)
            var buffer = source.buffer

            if ("gzip".equals(response.headers()["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }
            val charset: Charset =
                response.errorBody()?.contentType()?.charset(StandardCharsets.UTF_8)
                    ?: StandardCharsets.UTF_8
            if (response.errorBody()?.contentLength() != 0L) {
                buffer.clone().readString(charset)
            } else
                null
        }
    }

    val result = try {
        errorMessage?.let {
            val error = Json.parseToJsonElement(it).jsonObject["error"]?.jsonObject
            val message =
                error?.get("message")?.jsonPrimitive?.content ?: throw SerializationException()
            NetworkErrorException(code(), message)
        } ?: NetworkErrorException(code(), message())
    } catch (e: SerializationException) {
        NetworkErrorException(code(), message())
    } catch (e: IllegalStateException) {
        NetworkErrorException(code(), "IllegalArgumentException")
    } catch (e: Exception) {
        NetworkErrorException(code(), "Please try again later")
    }

    Log.d(this::class.simpleName, result.stackTraceToString())
    return result
}
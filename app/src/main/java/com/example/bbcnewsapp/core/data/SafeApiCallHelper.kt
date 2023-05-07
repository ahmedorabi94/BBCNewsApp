package com.example.bbcnewsapp.core.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {

    return withContext(dispatcher) {
        try {

            ResultWrapper.Success(apiCall.invoke())

        } catch (throwable: Throwable) {
            Timber.e("$throwable   ${throwable.localizedMessage}")
            when (throwable) {
                is IOException -> {
                    ResultWrapper.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.Error(code, errorResponse)
                }
                is KotlinNullPointerException -> {
                    ResultWrapper.NoContentError
                }
                else -> {
                    ResultWrapper.Error(null, null)
                }
            }
        }
    }

}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {


    val s = throwable.response()?.errorBody()?.string()
    val gson = JSONObject(s ?: "NULL")
    val message = gson.getString("message")

    Timber.e(s)
    Timber.e(message)
    return if (s != null) {
        ErrorResponse(message)
    } else {
        null
    }

}

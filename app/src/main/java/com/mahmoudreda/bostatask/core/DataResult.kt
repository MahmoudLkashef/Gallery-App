package com.mahmoudreda.bostatask.core

import retrofit2.HttpException
import retrofit2.Response

sealed class DataResult<out T>(
    private val data: T? = null
) {
    object Idle : DataResult<Nothing>()

    object Loading : DataResult<Nothing>()

    data class Error<T>(val throwable: Throwable) : DataResult<T>(
        data = null
    )

    data class Success<out T>(val data: T) : DataResult<T>(
        data = data
    )
}

fun <T> Response<T>.getDataResult(): DataResult<T> {
    val responseBody = body()
    return if (isSuccessResponse(responseBody))
        DataResult.Success(data = responseBody!!)
    else if (responseBody == null) DataResult.Error(throwable = IllegalArgumentException("Response with empty body"))
    else
        DataResult.Error(throwable = HttpException(this))
}

private fun <T> Response<T>.isSuccessResponse(responseBody: T?) =
    isSuccessful && responseBody != null
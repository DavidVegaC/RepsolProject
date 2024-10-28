package com.repsol.core_data.common.remote

import com.repsol.railway.Output
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

typealias DataOutput<T> = Output<T, DataError>

suspend fun <T> safeApiCall(
    context: CoroutineDispatcher = Dispatchers.IO,
    block: suspend () -> Response<T>
): DataOutput<T> {
    return try {
        val response = withContext(context) {
            block()
        }
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Output.Success(body)
            } else {
                Output.Failure(DataError.Default("Response body is null"))
            }
        } else {
            Output.Failure(DataError.Default(response.errorBody()?.string() ?: "Unknown error"))
        }
    } catch (e: Exception) {
        Output.Failure(DataError.Default(e.message ?: "Unknown error"))
    }
}
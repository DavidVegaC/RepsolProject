package com.repsol.gestor_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.Download
import com.repsol.railway.isSuccessful

sealed class PostDownloadResult {

    data class Success(val base64String: String): PostDownloadResult()

    data class Error(val message: String): PostDownloadResult()
}

fun DataOutput<Download>.handleResult(): PostDownloadResult {

    if (isSuccessful()) {
        val errorManager = data.errorManager
        return when {
            errorManager.code.contentEquals("0").not() -> PostDownloadResult.Error(message = errorManager.message.orEmpty())
            data.base64String.isBlank() -> PostDownloadResult.Error(message = errorManager.message.orEmpty())
            else -> PostDownloadResult.Success(data.base64String)
        }
    }

    val defaultError: DataError.Default = error as DataError.Default
    return PostDownloadResult.Error(message = defaultError.message)
}
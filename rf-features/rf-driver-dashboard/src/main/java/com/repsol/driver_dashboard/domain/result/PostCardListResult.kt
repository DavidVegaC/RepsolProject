package com.repsol.driver_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.driver_dashboard.domain.entity.DriverData
import com.repsol.railway.isSuccessful

sealed class PostCardListResult {
    data class Success(val data: DriverData) : PostCardListResult()
    data class Error(val message: String) : PostCardListResult()
}

fun DataOutput<DriverData>.handleResult(): PostCardListResult {
    if (this.isSuccessful()) {
        val creditBalance: DriverData = this.data
        val errorManager = creditBalance.errorManager
        if (errorManager.code.contentEquals("0").not()) {
            return PostCardListResult.Error(message = errorManager.message.orEmpty())
        }
        return PostCardListResult.Success(data)
    } else {
        val error: DataError.Default = this.error as DataError.Default
        return PostCardListResult.Error(message = error.message)
    }
}

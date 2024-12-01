package com.repsol.gestor_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.KpiData
import com.repsol.railway.isSuccessful

sealed class GetKpiResult {
    data class Success(val data: KpiData) : GetKpiResult()
    data class Error(val message: String) : GetKpiResult()
    data class ServiceError(val message: String) : GetKpiResult()
}

fun DataOutput<KpiData>.handleResult(): GetKpiResult {
    if (this.isSuccessful()) {
        val kpiData: KpiData = data
        val errorManager = kpiData.errorManager
        if (errorManager.code.contentEquals("0").not()) {
            return GetKpiResult.ServiceError(message = errorManager.message.orEmpty())
        }
        return GetKpiResult.Success(kpiData)
    } else {
        val error: DataError.Default = this.error as DataError.Default
        return GetKpiResult.Error(message = error.message)
    }
}
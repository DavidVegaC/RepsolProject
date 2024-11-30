package com.repsol.gestor_dashboard.data.mapper

import com.repsol.core_domain.common.error.GlobalError

object CardsMapper {

    fun toKpi(response: com.repsol.gestor_dashboard.data.remote.dto.response.kpi.KpiResponse): com.repsol.gestor_dashboard.domain.entity.KpiData {

        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )

        return com.repsol.gestor_dashboard.domain.entity.KpiData(
            countCanceled = response.data.countCanceled,
            countInactive = response.data.countInactive,
            countActive = response.data.countActive,
            errorManager = error,
        )
    }
}
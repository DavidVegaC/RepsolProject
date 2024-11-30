package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

class KpiData(
    val countActive: Int,
    val countInactive: Int,
    val countCanceled: Int,
    val errorManager: GlobalError,
)
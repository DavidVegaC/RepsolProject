package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

class VehicleList(
    val items: List<VehicleItem>,
    val errorManager: GlobalError,
)
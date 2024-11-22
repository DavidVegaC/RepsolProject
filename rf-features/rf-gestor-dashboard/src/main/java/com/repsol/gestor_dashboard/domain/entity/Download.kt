package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

class Download(
    val base64String: String,
    val errorManager: GlobalError,
)
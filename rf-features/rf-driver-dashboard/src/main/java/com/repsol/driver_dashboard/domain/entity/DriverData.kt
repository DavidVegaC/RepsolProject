package com.repsol.driver_dashboard.domain.entity

import com.repsol.core_domain.common.error.GlobalError

class DriverData(
    val driverName: String,
    val creationDate: String,
    val descriptionCenterCost: String,
    val featureDescription: String,
    val descriptionControlType: String,
    val cardNumber: String,
    val maxAmount: String,
    val actAmount: String,
    val errorManager: GlobalError,
)
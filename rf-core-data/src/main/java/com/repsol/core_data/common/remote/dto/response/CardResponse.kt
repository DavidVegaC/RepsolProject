package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class CardResponse(
    val driver: String,
    val creationDate: String,
    val descriptionCenterCost: String,
    val featureDescription: String,
    val descriptionControlType: String,
    val cardNumber: String,
    val maxAmount: String,
    val actAmount: String,
    val numberPlate: String,
    val statusCode: String,
)
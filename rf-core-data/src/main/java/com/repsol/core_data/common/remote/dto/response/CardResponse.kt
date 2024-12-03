package com.repsol.core_data.common.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
class CardResponse(
    val creationDate: String,
    val descriptionState: String,
    val descriptionCenterCost: String,
    val featureDescription: String,
    val descriptionControlType: String,
    val codeFeaturesCard: String,
    val numberDocument: String,
    val typeDocumentDescription: String,
    val cardNumber: String,
    val driver: String,
    val maxAmount: String,
    val actAmount: String,
    val numberPlate: String,
    val statusCode: String,
    val descriptionBusinessRule: String,
    val descriptionStateRequestPhysicalCard: String,
    val km: Boolean,
    val descriptionResetAmount: String,
)
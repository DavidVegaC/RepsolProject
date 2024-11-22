package com.repsol.driver_dashboard.data.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
class CardListRequest(
    val cardNumber: String,
    val plate: String,
    val driverName: String,
    val createDateFrom: String,
    val createDateTo: String,
    val listStatus: List<String>,
    val listCodeFeatureCard: List<String>,
    val listCodeAssignmentCard: List<String>,
    val listIdCenterCost: List<String>,
    val pageNumber: Int,
    val pageSize: Int,
    val numberDocument: String,
    val listStatePhysicalCard: List<String>,
    val listTypeAttention: List<String>,
    val listTypeDocument: List<String>,
    val listStatusMileage: List<String>,
    val cardTypes: String,
) {

    companion object {

        val defaultRequest = CardListRequest(
            cardNumber = "",
            plate = "",
            driverName = "",
            createDateFrom = "",
            createDateTo = "",
            listStatus = emptyList(),
            listCodeFeatureCard = emptyList(),
            listCodeAssignmentCard = emptyList(),
            listIdCenterCost = emptyList(),
            pageNumber = 1,
            pageSize = 100,
            numberDocument = "",
            listStatePhysicalCard = emptyList(),
            listTypeAttention = emptyList(),
            listTypeDocument = emptyList(),
            listStatusMileage = emptyList(),
            cardTypes = "2",
        )
    }
}
package com.repsol.gestor_dashboard.ui.cards.interactor

import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_domain.common.ActionCardModel
import com.repsol.core_domain.common.entities.ServiceStatus
import com.repsol.core_platform.handler.UiState
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.rf_assets.R
import com.repsol.tools.utils.EMPTY_STRING

data class CardsUiState(
    val kpiStatus: ServiceStatus = ServiceStatus.BLANK,
    val active: Int = 0,
    val inactive: Int = 0,
    val canceled: Int = 0,
    val searchText: String = EMPTY_STRING,
    val totalOptions: List<String> = listOf("Vehiculo", "Conductor", "Pool"),
    val selectedOptions: List<String> = emptyList(),
    val cards: List<CardItem> = emptyList(),
    val cardNumber: String = EMPTY_STRING,
    val plate: String = EMPTY_STRING,
    val driverName: String = EMPTY_STRING,
    val createDateFrom: String = EMPTY_STRING,
    val createDateTo: String = EMPTY_STRING,
    val listStatus: List<String> = emptyList(),
    val listCodeFeatureCard: List<String> = emptyList(),
    val listCodeAssignmentCard: List<String> = emptyList(),
    val listIdCenterCost: List<String> = emptyList(),
    val pageNumber: Int = 0,
    val numberDocument: String = EMPTY_STRING,
    val listStatePhysicalCard: List<String> = emptyList(),
    val listTypeAttention: List<String> = emptyList(),
    val listTypeDocument: List<String> = emptyList(),
    val listStatusMileage: List<String> = emptyList(),
    val currentPage: Int = 0,
    val totalRows: Int = 0,
    val pageSize: Int = 0,
    val totalPage: Int = 0,
): UiState {
    val actionCards: List<ActionCardModel> = listOf(
        ActionCardModel(id = 1, icon = R.drawable.ic_add, title = R.string.new_card),
        ActionCardModel(id = 2, icon = R.drawable.ic_pay, title = R.string.qr),
        ActionCardModel(id = 3, icon = R.drawable.ic_pay, title = R.string.assign_balance),
        ActionCardModel(id = 4, icon = R.drawable.ic_options, title = R.string.see_all)
    )

    val isEnabledPreviousPaginate: Boolean
        get() = currentPage > INITIAL_PAGE

    val isEnabledNextPaginate: Boolean
        get() = currentPage < totalPage

    val requestForCardList = CardListRequest(
        cardNumber = cardNumber,
        plate = plate,
        driverName = driverName,
        createDateFrom = createDateFrom,
        createDateTo = createDateTo,
        listStatus = listStatus,
        listCodeFeatureCard = listCodeFeatureCard,
        listCodeAssignmentCard = listCodeAssignmentCard,
        listIdCenterCost = listIdCenterCost,
        pageNumber = pageNumber,
        pageSize = PAGE_SIZE,
        numberDocument = numberDocument,
        listStatePhysicalCard = listStatePhysicalCard,
        listTypeAttention = listTypeAttention,
        listTypeDocument = listTypeDocument,
        listStatusMileage = listStatusMileage,
        cardTypes = "2",
    )

    companion object {

        const val PAGE_SIZE = 5
        const val INITIAL_PAGE = 1
    }
}
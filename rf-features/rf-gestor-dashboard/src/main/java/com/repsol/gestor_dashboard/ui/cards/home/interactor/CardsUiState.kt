package com.repsol.gestor_dashboard.ui.cards.home.interactor

import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_domain.common.ActionCardModel
import com.repsol.core_domain.common.entities.AttentionType
import com.repsol.core_domain.common.entities.CardFeature
import com.repsol.core_domain.common.entities.CardState
import com.repsol.core_domain.common.entities.CostCenter
import com.repsol.core_domain.common.entities.DocumentType
import com.repsol.core_domain.common.entities.MileageStatus
import com.repsol.core_domain.common.entities.PhysicalCardState
import com.repsol.core_domain.common.entities.ServiceStatus
import com.repsol.core_platform.handler.UiState
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.rf_assets.R
import com.repsol.tools.utils.EMPTY_STRING
import com.repsol.tools.utils.convertDateToStringFormat
import java.util.Date

data class CardsUiState(
    val kpiStatus: ServiceStatus = ServiceStatus.BLANK,
    val active: Int = 0,
    val inactive: Int = 0,
    val canceled: Int = 0,
    val selectedOptions: List<String> = emptyList(),
    val cards: List<CardItem> = emptyList(),

    //Campos para armar el request
    val cardNumber: String = EMPTY_STRING,
    val plate: String = EMPTY_STRING,
    val listIdCenterCost: List<String> = emptyList(),
    val listCodeAssignmentCard: List<String> = emptyList(),
    val selectedCardFeatures: List<CardFeature> = emptyList(),
    val startDate: Date? = null,
    val endDate: Date? = null,
    val selectedCardStates: List<CardState> = emptyList(),
    val driverName: String = EMPTY_STRING,
    val selectedDocumentTypes: List<DocumentType> = emptyList(),
    val numberDocument: String = EMPTY_STRING,
    val selectedAttentionTypes: List<AttentionType> = emptyList(),
    val selectedPhysicalCardStates: List<PhysicalCardState> = emptyList(),
    val selectedMileageStatus: List<MileageStatus> = emptyList(),
    val selectedCostCenter: List<CostCenter> = emptyList(),
    val pageNumber: Int = 0,
    val currentPage: Int = 0,
    val totalRows: Int = 0,
    val pageSize: Int = 0,
    val totalPage: Int = 0,
    val isLoadingFilter: Boolean = false,
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
        createDateFrom = convertDateToStringFormat(startDate),
        createDateTo = convertDateToStringFormat(endDate),
        listStatus = selectedCardStates.map { it.code },
        listCodeFeatureCard = selectedCardFeatures.map { it.code },
        listCodeAssignmentCard = listCodeAssignmentCard,
        listIdCenterCost = selectedCostCenter.map { it.code },
        pageNumber = pageNumber,
        pageSize = PAGE_SIZE,
        numberDocument = numberDocument,
        listStatePhysicalCard = selectedPhysicalCardStates.map { it.code },
        listTypeAttention = selectedAttentionTypes.map { it.code },
        listTypeDocument = selectedDocumentTypes.map { it.code },
        listStatusMileage = selectedMileageStatus.map { it.code },
        cardTypes = "2",
    )

    //campos para el filtro
    val cardFeatures: List<CardFeature> = CardFeature.entries
    val cardStates: List<CardState> = CardState.entries
    val documentTypes: List<DocumentType> = DocumentType.entries
    val attentionTypes: List<AttentionType> = AttentionType.entries
    val physicalCardStates: List<PhysicalCardState> = PhysicalCardState.entries
    val mileageStatus: List<MileageStatus> = MileageStatus.entries
    val costCenter: List<CostCenter> = CostCenter.entries

    companion object {

        const val PAGE_SIZE = 5
        const val INITIAL_PAGE = 1
    }
}
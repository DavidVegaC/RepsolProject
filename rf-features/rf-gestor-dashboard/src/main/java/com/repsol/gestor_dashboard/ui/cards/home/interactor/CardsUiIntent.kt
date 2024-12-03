package com.repsol.gestor_dashboard.ui.cards.home.interactor

import com.repsol.core_domain.common.entities.AttentionType
import com.repsol.core_domain.common.entities.CardFeature
import com.repsol.core_domain.common.entities.CardState
import com.repsol.core_domain.common.entities.DocumentType
import com.repsol.core_domain.common.entities.MileageStatus
import com.repsol.core_domain.common.entities.PhysicalCardState
import com.repsol.core_platform.handler.UiIntent
import com.repsol.gestor_dashboard.domain.entity.CardItem
import java.util.Date

sealed class CardsUiIntent: UiIntent {
    data object LoadKpi: CardsUiIntent()
    data object LoadInitialCards: CardsUiIntent()
    data class UpdateSearchText(val newValue: String): CardsUiIntent()
    data object LoadPreviousPaginate: CardsUiIntent()
    data object LoadNextPaginate: CardsUiIntent()
    data class GoToDetail(val item: CardItem): CardsUiIntent()
    data object GoToFilter: CardsUiIntent()
    data object LoadCardsBySearch: CardsUiIntent()
    
    //Sección filtrado
    data object SelectAllCardFeatureOption: CardsUiIntent()
    data class AddSelectedCardFeature(val option: CardFeature): CardsUiIntent()
    data class RemoveSelectedCardFeature(val option: CardFeature): CardsUiIntent()
    data class OnChangeValueForDateRange(val startDate: Date, val endDate: Date): CardsUiIntent()
    data object SelectAllCardStateOption: CardsUiIntent()
    data class AddSelectedCardState(val option: CardState): CardsUiIntent()
    data class RemoveSelectedCardState(val option: CardState): CardsUiIntent()
    data class UpdateDriverName(val newValue: String): CardsUiIntent()
    data object SelectAllDocumentTypeOption: CardsUiIntent()
    data class AddSelectedDocumentType(val option: DocumentType): CardsUiIntent()
    data class RemoveSelectedDocumentType(val option: DocumentType): CardsUiIntent()
    data class UpdateDocumentNumber(val newValue: String): CardsUiIntent()
    data object SelectAllAttentionTypeOption: CardsUiIntent()
    data class AddSelectedAttentionType(val option: AttentionType): CardsUiIntent()
    data class RemoveSelectedAttentionType(val option: AttentionType): CardsUiIntent()
    data object SelectAllPhysicalCardStateOption: CardsUiIntent()
    data class AddSelectedPhysicalCardState(val option: PhysicalCardState): CardsUiIntent()
    data class RemoveSelectedPhysicalCardState(val option: PhysicalCardState): CardsUiIntent()
    data object SelectAllMileageStatusOption: CardsUiIntent()
    data class AddSelectedMileageStatus(val option: MileageStatus): CardsUiIntent()
    data class RemoveSelectedMileageStatus(val option: MileageStatus): CardsUiIntent()
    data object ApplyFilters: CardsUiIntent()
    data object CleanFilters: CardsUiIntent()
}
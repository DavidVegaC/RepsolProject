package com.repsol.gestor_dashboard.domain.entity

import com.repsol.core_domain.common.entities.PaginationState
import com.repsol.core_domain.common.error.GlobalError

class CardList(
    val items: List<CardItem>,
    val pagination: PaginationState,
    val errorManager: GlobalError,
)
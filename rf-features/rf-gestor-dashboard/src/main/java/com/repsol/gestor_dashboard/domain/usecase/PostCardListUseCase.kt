package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.gestor_dashboard.domain.repository.CardsRepository
import com.repsol.gestor_dashboard.domain.result.PostCardListResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import javax.inject.Inject

class PostCardListUseCase @Inject constructor(
    private val repository: CardsRepository,
): SimpleUseCase.ParamsAndResult<CardListRequest, PostCardListResult> {

    override suspend fun invoke(params: CardListRequest): PostCardListResult {
        //val clientId: String = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
        //val email: String = SessionStorage.getString(UserSession.EMAIL).orEmpty()
        val clientId = "0348117784"
        val email = "HMORANCU@EVERIS.COM"
        return repository.postCardList(clientId, email, params).handleResult()
    }
}
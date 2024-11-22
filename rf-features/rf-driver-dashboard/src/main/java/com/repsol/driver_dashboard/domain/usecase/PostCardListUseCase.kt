package com.repsol.driver_dashboard.domain.usecase

import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.driver_dashboard.domain.repository.IndexRepository
import com.repsol.driver_dashboard.domain.result.PostCardListResult
import com.repsol.driver_dashboard.domain.result.handleResult
import javax.inject.Inject

class PostCardListUseCase @Inject constructor(
    private val repository: IndexRepository,
): SimpleUseCase.OnlyResult<PostCardListResult> {

    override suspend fun invoke(): PostCardListResult {
        //val clientId: String = SessionStorage.getString(UserSession.ID_CLIENT).orEmpty()
        //val email: String = SessionStorage.getString(UserSession.EMAIL).orEmpty()
        val clientId = "0348118803"
        val email = "HMORANCU@EVERIS.COM"
        return repository.postCardList(clientId, email).handleResult()
    }

}
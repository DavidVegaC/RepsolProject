package com.repsol.gestor_dashboard.domain.usecase

import com.repsol.core_domain.common.use_case.SimpleUseCase
import com.repsol.gestor_dashboard.domain.repository.IndexRepository
import com.repsol.gestor_dashboard.domain.result.PostDownloadResult
import com.repsol.gestor_dashboard.domain.result.handleResult
import javax.inject.Inject

class PostDownloadUseCase @Inject constructor(
    private val repository: IndexRepository,
): SimpleUseCase.OnlyResult<PostDownloadResult> {

    override suspend fun invoke(): PostDownloadResult {
        //val clientId: String = SessionStorage.getString("idClient").orEmpty()
        val clientId = "0348117784"
        return repository.postDownload(clientId).handleResult()
    }
}
package com.repsol.driver_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.driver_dashboard.data.mapper.IndexMapper.toCardList
import com.repsol.driver_dashboard.data.remote.data_source.IndexRemoteDS
import com.repsol.driver_dashboard.data.remote.dto.request.CardListRequest
import com.repsol.driver_dashboard.domain.entity.DriverData
import com.repsol.driver_dashboard.domain.repository.IndexRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class IndexRepositoryImpl @Inject constructor(
    private val remote: IndexRemoteDS,
): IndexRepository {

    override suspend fun postCardList(clientId: String, userEmail: String): DataOutput<DriverData> {
        val request: CardListRequest = CardListRequest.defaultRequest
        val dataOutput = remote.postCardList(clientId, userEmail, request)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val download: DriverData = toCardList(dataOutput.asSuccessful().data)
        return Output.Success(download)
    }
}
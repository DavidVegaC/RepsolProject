package com.repsol.driver_dashboard.data.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.dto.request.CardListRequest
import com.repsol.core_data.common.remote.shared.data_source.DefaultSharedRemoteDS
import com.repsol.driver_dashboard.data.mapper.IndexMapper.toCardList
import com.repsol.driver_dashboard.domain.entity.DriverData
import com.repsol.driver_dashboard.domain.repository.IndexRepository
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject

class IndexRepositoryImpl @Inject constructor(
    private val sharedRemote: DefaultSharedRemoteDS,
): IndexRepository {

    override suspend fun postCardList(clientId: String, userEmail: String): DataOutput<DriverData> {
        val request: CardListRequest = CardListRequest.defaultRequest
        val dataOutput = sharedRemote.postCardList(clientId, userEmail, request)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }

        val download: DriverData = toCardList(dataOutput.asSuccessful().data)
        return Output.Success(download)
    }
}
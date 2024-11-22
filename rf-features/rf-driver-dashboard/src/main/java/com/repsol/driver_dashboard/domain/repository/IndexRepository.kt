package com.repsol.driver_dashboard.domain.repository

import com.repsol.core_data.common.remote.DataOutput
import com.repsol.driver_dashboard.domain.entity.DriverData

interface IndexRepository {

    suspend fun postCardList(clientId: String, userEmail: String): DataOutput<DriverData>
}
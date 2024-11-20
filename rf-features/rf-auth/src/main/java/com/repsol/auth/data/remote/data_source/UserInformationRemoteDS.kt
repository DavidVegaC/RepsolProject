package com.repsol.auth.data.remote.data_source

import com.repsol.auth.data.remote.dto.response.UserInformationResponse
import com.repsol.core_data.common.remote.DataOutput

interface UserInformationRemoteDS {

    suspend fun getUserInformation(email: String): DataOutput<UserInformationResponse>
}
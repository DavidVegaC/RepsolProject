package com.repsol.auth.data.remote.data_source

import com.repsol.auth.data.remote.api.UserInformationApi
import com.repsol.auth.data.remote.dto.response.UserInformationResponse
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_data.common.remote.safeApiCall
import javax.inject.Inject

class DefaultUserInformationRemoteDS @Inject constructor(
    private val api: UserInformationApi
): UserInformationRemoteDS {

    override suspend fun getUserInformation(email: String): DataOutput<UserInformationResponse> {
        return safeApiCall { api.getUserInformation(email) }
    }
}
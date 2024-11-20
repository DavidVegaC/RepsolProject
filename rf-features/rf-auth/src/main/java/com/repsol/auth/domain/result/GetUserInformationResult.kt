package com.repsol.auth.domain.result

import com.repsol.auth.domain.entity.UserInformation
import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_domain.common.entities.UserProfile
import com.repsol.railway.isSuccessful

sealed class GetUserInformationResult {

    data object IsGestor: GetUserInformationResult()

    data object IsDriver: GetUserInformationResult()

    data class Error(val message: String): GetUserInformationResult()
}

fun DataOutput<UserInformation>.handleResult(): GetUserInformationResult = when {
        isSuccessful() -> {
            val userInformation: UserInformation = this.data
            val errorManager = userInformation.errorManager
            if (errorManager.code.contentEquals("0")) {
                GetUserInformationResult.Error(message = errorManager.message.orEmpty())
            }

            if (userInformation.client == null) {
                GetUserInformationResult.Error(message = "")
            } else if (UserProfile.isGestor(userInformation.client.profileId)) {
                GetUserInformationResult.IsGestor
            } else {
                GetUserInformationResult.IsDriver
            }
        }

        else -> {
            val error: DataError.Default = this.error as DataError.Default
            GetUserInformationResult.Error(message = error.message)
        }
}
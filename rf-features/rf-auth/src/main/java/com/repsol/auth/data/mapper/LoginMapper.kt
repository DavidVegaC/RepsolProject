package com.repsol.auth.data.mapper

import com.gigya.android.sdk.account.models.GigyaAccount
import com.repsol.auth.data.remote.dto.response.ClientResponse
import com.repsol.auth.data.remote.dto.response.ProfileResponse
import com.repsol.auth.data.remote.dto.response.UserInformationResponse
import com.repsol.auth.domain.entity.Client
import com.repsol.auth.domain.entity.Profile
import com.repsol.auth.domain.entity.UserInformation
import com.repsol.core_domain.common.entities.UserProfile
import com.repsol.core_domain.common.error.GlobalError

object LoginMapper {

    fun toProfile(gigyaAccount: GigyaAccount): Profile {
        return Profile(uid = gigyaAccount.uid.orEmpty())
    }

    fun toUserInformation(response: UserInformationResponse): UserInformation {
        val error = GlobalError(
            code = response.error_manager.errorNumber.toString(),
            message = response.error_manager.description,
        )
        val listClient: List<Client> = response.data.listClient?.mapNotNull(::toClient) ?: emptyList()
        val client: Client? = listClient.firstOrNull()

        return UserInformation(
            client = client,
            name = response.data.name.orEmpty(),
            lastName = response.data.lastName.orEmpty(),
            errorManager = error,
        )
    }

    private fun toClient(response: ClientResponse?): Client? {
        if (response == null) return null

        val validProfile: Int = getValidProfile(response.listProfile) ?: return null
        return Client(profileId = validProfile, idClient = response.idClient.orEmpty(), businessName = response.businessName.orEmpty())
    }

    private fun getValidProfile(list: List<ProfileResponse?>?): Int? {
        if (list.isNullOrEmpty()) return null

        return list.firstOrNull { UserProfile.isValid(it?.idProfile ?: UserProfile.NONE.id ) }?.idProfile
    }
}
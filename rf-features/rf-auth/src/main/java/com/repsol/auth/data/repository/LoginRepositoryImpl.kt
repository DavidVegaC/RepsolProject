package com.repsol.auth.data.repository

import com.gigya.android.sdk.Gigya
import com.gigya.android.sdk.GigyaLoginCallback
import com.gigya.android.sdk.account.models.GigyaAccount
import com.gigya.android.sdk.network.GigyaError
import com.repsol.auth.domain.entity.Profile
import com.repsol.auth.data.mapper.LoginMapper.toProfile
import com.repsol.auth.data.mapper.LoginMapper.toUserInformation
import com.repsol.auth.data.remote.data_source.UserInformationRemoteDS
import com.repsol.auth.domain.entity.UserInformation
import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.core_domain.storage.SessionStorage
import com.repsol.railway.Output
import com.repsol.railway.asFailure
import com.repsol.railway.asSuccessful
import com.repsol.railway.isFailure
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepositoryImpl @Inject constructor(
    private val remote: UserInformationRemoteDS,
): LoginRepository {

    // Referencing the gigya instance.
    private val gigyaInstance: Gigya<GigyaAccount> = Gigya.getInstance(GigyaAccount::class.java)

    override suspend fun loginWith(
        email: String,
        password: String,
    ): Output<Profile, GlobalError> = suspendCoroutine { continuation ->
        //"testregflotas1@mailinator.com" - "testregflotas1@mailinator.com"
        gigyaInstance.login(/* loginId = */ "testregflotas1@mailinator.com",
            /* password = */ "testregflotas1@mailinator.com",
            /* gigyaCallback = */ object : GigyaLoginCallback<GigyaAccount>() {
                override fun onSuccess(gigya: GigyaAccount) {
                    // Success
                    val profile: Profile = toProfile(gigya)
                    SessionStorage.setString("uid", profile.uid)
                    continuation.resume(Output.Success(profile))
                }

                override fun onError(error: GigyaError) {
                    // Fail
                    val globalError = GlobalError(
                        code = error.errorCode.toString(),
                        message = error.localizedMessage,
                    )
                    continuation.resume(Output.Failure(globalError))
                }
            }
        )
    }

    override suspend fun getUserInformation(email: String): DataOutput<UserInformation> {
        val dataOutput = remote.getUserInformation(email)
        if (dataOutput.isFailure()) {
            return dataOutput.asFailure()
        }
        val clientInformation: UserInformation = toUserInformation(dataOutput.asSuccessful().data)
        if (clientInformation.client != null) {
            SessionStorage.setString("idClient", clientInformation.client.idClient)
            SessionStorage.setString("name", clientInformation.name)
            SessionStorage.setString("lastName", clientInformation.name)
            SessionStorage.setString("businessName", clientInformation.client.businessName)
        }

        return Output.Success(clientInformation)
    }
}
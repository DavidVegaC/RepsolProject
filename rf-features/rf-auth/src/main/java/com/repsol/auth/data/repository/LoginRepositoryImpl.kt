package com.repsol.auth.data.repository

import com.gigya.android.sdk.Gigya
import com.gigya.android.sdk.GigyaLoginCallback
import com.gigya.android.sdk.account.models.GigyaAccount
import com.gigya.android.sdk.network.GigyaError
import com.repsol.auth.domain.entity.Profile
import com.repsol.auth.data.mapper.LoginMapper.toProfile
import com.repsol.auth.domain.repository.LoginRepository
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.railway.Output
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepositoryImpl @Inject constructor(): LoginRepository {

    // Referencing the gigya instance.
    private val gigyaInstance: Gigya<GigyaAccount> = Gigya.getInstance(GigyaAccount::class.java)

    override suspend fun loginWith(
        email: String,
        password: String,
    ): Output<Profile, GlobalError> = suspendCoroutine { continuation ->
        gigyaInstance.login(/* loginId = */ "testregflotas1@mailinator.com",
            /* password = */ "testregflotas1@mailinator.com",
            /* gigyaCallback = */ object : GigyaLoginCallback<GigyaAccount>() {
                override fun onSuccess(gigya: GigyaAccount) {
                    // Success
                    val profile: Profile = toProfile(gigya)
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

}
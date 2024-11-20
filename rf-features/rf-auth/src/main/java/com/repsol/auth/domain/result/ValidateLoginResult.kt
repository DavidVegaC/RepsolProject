package com.repsol.auth.domain.result

import com.repsol.auth.domain.entity.Profile
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.railway.Output
import com.repsol.railway.isSuccessful

sealed class ValidateLoginResult {

    data class Success(val profile: Profile): ValidateLoginResult()

    data object LoginError: ValidateLoginResult()

    data object GeneralError: ValidateLoginResult()
}

fun Output<Profile, GlobalError>.handleResult(): ValidateLoginResult = when {
    isSuccessful() -> {
        ValidateLoginResult.Success(profile = this.data)
    }
    else -> {
        val error: GlobalError = this.error
        if (error.code == "403042") {
            ValidateLoginResult.LoginError
        }
        ValidateLoginResult.GeneralError
    }
}


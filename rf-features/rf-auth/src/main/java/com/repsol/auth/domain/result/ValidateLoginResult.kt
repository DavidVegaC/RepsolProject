package com.repsol.auth.domain.result

import com.repsol.auth.domain.entity.Profile
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.railway.Output
import com.repsol.railway.isFailure
import com.repsol.railway.isSuccessful

sealed class ValidateLoginResult {

    data class Success(val profile: Profile): ValidateLoginResult()

    data class Error(val code: String, val message: String): ValidateLoginResult()

    data object ErrorDefault: ValidateLoginResult()
}

fun Output<Profile, GlobalError>.handleResult(): ValidateLoginResult = when {
    isSuccessful() -> {
        ValidateLoginResult.Success(profile = this.data)
    }
    isFailure() -> {
        val error: GlobalError = this.error
        ValidateLoginResult.Error(code = error.code.orEmpty(), message = error.message.orEmpty())
    }
    else -> ValidateLoginResult.ErrorDefault
}


package com.repsol.auth.domain.repository

import com.repsol.auth.domain.entity.Profile
import com.repsol.core_domain.common.error.GlobalError
import com.repsol.railway.Output

interface LoginRepository {

    suspend fun loginWith(email: String, password: String): Output<Profile, GlobalError>
}
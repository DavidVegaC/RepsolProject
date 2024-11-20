package com.repsol.auth.domain.entity

import com.repsol.core_domain.common.error.GlobalError

class UserInformation(
    val client: Client?,
    val name: String,
    val lastName: String,
    val errorManager: GlobalError,
)
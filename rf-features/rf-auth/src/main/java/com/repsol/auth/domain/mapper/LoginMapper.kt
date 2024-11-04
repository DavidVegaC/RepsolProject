package com.repsol.auth.domain.mapper

import com.gigya.android.sdk.account.models.GigyaAccount
import com.repsol.auth.domain.entity.Profile

object LoginMapper {

    fun toProfile(gigyaAccount: GigyaAccount): Profile {
        return Profile(uid = gigyaAccount.uid.orEmpty())
    }
}
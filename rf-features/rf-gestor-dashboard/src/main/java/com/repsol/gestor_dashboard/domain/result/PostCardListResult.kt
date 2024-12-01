package com.repsol.gestor_dashboard.domain.result

import com.repsol.core_data.common.remote.DataError
import com.repsol.core_data.common.remote.DataOutput
import com.repsol.gestor_dashboard.domain.entity.CardList
import com.repsol.railway.isSuccessful

sealed class PostCardListResult {
    data class Success(val data: CardList) : PostCardListResult()
    data class Error(val message: String) : PostCardListResult()
    data class ServiceError(val message: String) : PostCardListResult()
}

fun DataOutput<CardList>.handleResult(): PostCardListResult {
    if (this.isSuccessful()) {
        val cardList: CardList = this.data
        val errorManager = cardList.errorManager
        if (errorManager.code.contentEquals("0").not()) {
            return PostCardListResult.ServiceError(message = errorManager.message.orEmpty())
        }
        return PostCardListResult.Success(cardList)
    } else {
        val error: DataError.Default = error as DataError.Default
        return PostCardListResult.Error(message = error.message)
    }
}
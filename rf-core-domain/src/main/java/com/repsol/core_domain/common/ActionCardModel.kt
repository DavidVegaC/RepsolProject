package com.repsol.core_domain.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class ActionCardModel(
    val id : Int,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val onClick: (() -> Unit) = {},
)
package com.repsol.components.style

import androidx.compose.ui.text.font.Font
import com.repsol.rf_components.R

enum class RFFont(
    val font: Font
) {
    Repsol(
        font = Font(R.font.repsol)
    ),
    RepsolBold(
        font = Font(R.font.repsol_bold)
    ),
    RepsolItalic(
        font = Font(R.font.repsol_italic)
    ),
    RepsolLight(
        font = Font(R.font.repsol_light)
    ),
    RepsolLightItalic(
        font = Font(R.font.repsol_light_italic)
    ),
    Roboto(
        font = Font(R.font.roboto)
    ),
    RobotoMedium(
        font = Font(R.font.roboto_medium)
    ),
    RobotoBold(
        font = Font(R.font.roboto_bold)
    )
}


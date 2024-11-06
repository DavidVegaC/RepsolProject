package com.repsol.components.button.style

import com.repsol.components.button.entity.RFColorState
import com.repsol.components.style.RFColor

enum class RFButtonOnColor {
    Primary,
    Background;

    fun getPrimaryColorState(): RFColorState {
        return when (this) {
            Primary -> RFColorState(
                enabledColor = RFColor.UxComponentColorWhite,
                textEnabled = RFColor.UxComponentColorBlueLagoon,
                disabledColor = RFColor.UxComponentColorDarkGray,
                textColorDisabled = RFColor.UxComponentColorWhite,
            )

            Background -> RFColorState(
                enabledColor = RFColor.UxComponentColorBlueLagoon,
                textEnabled = RFColor.UxComponentColorWhite,
                disabledColor = RFColor.UxComponentColorGainsboro,
                textColorDisabled = RFColor.UxComponentColorDarkGray,
            )
        }
    }

    fun getSecondaryColorState(): RFColorState {
        return when (this) {
            Primary -> RFColorState(
                enabledColor = RFColor.UxComponentColorWhite,
                textEnabled = RFColor.UxComponentColorBlueLagoon,
                disabledColor = RFColor.UxComponentColorDarkGray,
                textColorDisabled = RFColor.UxComponentColorWhite,
            )

            Background -> RFColorState(
                enabledColor = RFColor.UxComponentColorBlueLagoon,
                textEnabled = RFColor.UxComponentColorWhite,
                disabledColor = RFColor.UxComponentColorGainsboro,
                textColorDisabled = RFColor.UxComponentColorDarkGray,
            )
        }
    }

    fun getTextColorState(): RFColorState {
        return when (this) {
            Primary -> RFColorState(
                enabledColor = RFColor.UxComponentColorWhite,
                textEnabled = RFColor.UxComponentColorBlueLagoon,
                disabledColor = RFColor.UxComponentColorDarkGray,
                textColorDisabled = RFColor.UxComponentColorWhite,
            )

            Background -> RFColorState(
                enabledColor = RFColor.UxComponentColorBlueLagoon,
                textEnabled = RFColor.UxComponentColorWhite,
                disabledColor = RFColor.UxComponentColorGainsboro,
                textColorDisabled = RFColor.UxComponentColorDarkGray,
            )
        }
    }
}
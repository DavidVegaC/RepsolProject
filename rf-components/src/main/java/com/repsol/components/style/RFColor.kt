package com.repsol.components.style

import androidx.compose.ui.graphics.Color

sealed class RFColor(val color: Color) {

    object UxComponentColorSafetyOrange: RFColor(Color(0xFFFF6200))
    object UxComponentColorDarkOrange: RFColor(Color(0xFFFF8200))
    object UxComponentColorWhite : RFColor(Color(0xFFFFFFFF))
    object UxComponentColorWhiteSmoke : RFColor(Color(0xFFF2F2F2))
    object UxComponentColorEasternBlue: RFColor(Color(0xFF00809A))
    object UxComponentColorBlueLagoon: RFColor(Color(0xFF016A7F))
    object UxComponentColorMidnightBlue: RFColor(Color(0xFF041E42))
    object UxComponentColorCerulean: RFColor(Color(0xFF02526F))
    object UxComponentColorCGBlue: RFColor(Color(0xFF00859B))
    object UxComponentColorLightGray : RFColor(Color(0xFFCCCCCC))
    object UxComponentColorGrey : RFColor(Color(0xFF757575))
    object UxComponentColorDarkGray: RFColor(Color(0xFFAAAAAA))
    object UxComponentColorGainsboro: RFColor(Color(0xFFDDDDDD))
    object UxComponentColorCharcoal: RFColor(Color(0xFF464646))
    object UxComponentColorDiamond: RFColor(Color(0xFFC0EBF2))
    object UxComponentColorRed: RFColor(Color(0xFFE4002B))
    object UxComponentColorMistyRose: RFColor(Color(0xFFFCE5E9))
    object UxComponentColorWarning: RFColor(Color(0xFFE56565))
    object UxComponentColorLightPink: RFColor(Color(0xFFFF495A6))
    object UxComponentColorIrisBlue: RFColor(Color(0xFF00ADC3))
    object UxComponentColorPowderBlue: RFColor(Color(0xFFC0EBF2))
    object UxComponentColorDarkCerulean: RFColor(Color(0xFF004B8C))
    object UxComponentColorBlackPearl : RFColor(Color(0xFF0F191E))
    object UxComponentColorGreen : RFColor(Color(0xFF00A074))
    object UxComponentRippleColor : RFColor(Color(0x00FFFFFF))
    object UxComponentRippleColorOnPrimary : RFColor(Color(0x00FFFFFF))
    object UxComponentColorLinen : RFColor(Color(0xFFFFF0E0))
    object UxComponentColorTransparent : RFColor(Color(0x00000000))
    object UxComponentColorNickel : RFColor(Color(0xFF727272))
    class CustomColor(color: Color) : RFColor(color)
}


package com.repsol.components.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class RFTextStyle(var style: TextStyle) {

    class Repsol(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.Repsol.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RepsolBold(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RepsolBold.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RepsolItalic(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RepsolItalic.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RepsolLight(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RepsolLight.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RepsolLightItalic(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RepsolLightItalic.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class Roboto(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.Roboto.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RobotoMedium(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RobotoMedium.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RobotoBold(
        color: RFColor = RFColor.UxComponentColorEasternBlue,
        fontSize: TextUnit = 12.sp,
        textAlign: TextAlign = TextAlign.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        fontSynthesis: FontSynthesis? = null,
        fontFeatureSettings: String? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        baselineShift: BaselineShift? = null,
        textGeometricTransform: TextGeometricTransform? = null,
        localeList: LocaleList? = null,
        background: Color = Color.Unspecified,
        textDecoration: TextDecoration? = null,
        shadow: Shadow? = null,
        drawStyle: DrawStyle? = null,
        textDirection: TextDirection = TextDirection.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textIndent: TextIndent? = null,
        platformStyle: PlatformTextStyle? = null,
        lineHeightStyle: LineHeightStyle? = null,
        lineBreak: LineBreak = LineBreak.Unspecified,
        hyphens: Hyphens = Hyphens.Unspecified,
        textMotion: TextMotion? = null,
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.RobotoBold.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color,
            textAlign = textAlign,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontSynthesis = fontSynthesis,
            fontFeatureSettings = fontFeatureSettings,
            letterSpacing = letterSpacing,
            baselineShift = baselineShift,
            textGeometricTransform = textGeometricTransform,
            localeList = localeList,
            background = background,
            textDecoration = textDecoration,
            shadow = shadow,
            platformStyle = platformStyle,
            drawStyle = drawStyle,
            textDirection = textDirection,
            lineHeight = lineHeight,
            textIndent = textIndent,
            lineHeightStyle = lineHeightStyle,
            lineBreak = lineBreak,
            hyphens = hyphens,
            textMotion = textMotion
        )
    )

    class RFButtonTextStyle(
        color: RFColor? = null,
        fontSize: TextUnit = 18.sp
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.Roboto.font.toFontFamily(),
            fontSize = fontSize,
            color = color?.color ?: Color.Unspecified
        )
    )

    class RFInputLabelStyle(
        color: RFColor = RFColor.UxComponentColorCharcoal,
        fontSize: TextUnit = 16.sp
    ) : RFTextStyle(
        TextStyle(
            fontFamily = RFFont.Repsol.font.toFontFamily(),
            fontSize = fontSize,
            color = color.color
        )
    )

    class CustomTextStyle(textStyle: TextStyle) : RFTextStyle(textStyle)
}
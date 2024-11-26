package com.repsol.components.placeholder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor

@Composable
fun RFCardPlaceholder(
    modifier: Modifier = Modifier,
    backgroundColor: Color = RFColor.UxComponentColorWhite.color,
    padding: Dp = 16.dp,
    height: Dp = 300.dp,
    cornerRadius: Dp = 16.dp,
    indicatorColor: Color = RFColor.UxComponentColorDarkOrange.color,
    indicatorSize: Dp = 48.dp,
    strokeWidth: Dp = 4.dp,
    showLoading: Boolean = true,
){

    RFCardPlaceholderImpl(
        modifier = modifier,
        backgroundColor = backgroundColor,
        padding = padding,
        height = height,
        cornerRadius = cornerRadius,
        contentAlignment = Alignment.Center,
        indicatorColor = indicatorColor,
        indicatorSize = indicatorSize,
        strokeWidth = strokeWidth,
        showLoading = showLoading,
    )

}

@Composable
private fun RFCardPlaceholderImpl(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    padding: Dp,
    height: Dp,
    cornerRadius: Dp,
    contentAlignment: Alignment = Alignment.Center,
    indicatorColor: Color,
    indicatorSize: Dp,
    strokeWidth: Dp,
    showLoading: Boolean,
){

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(height)
            .background(backgroundColor, shape = RoundedCornerShape(cornerRadius))
            .padding(padding),
        contentAlignment = contentAlignment
    ){
        if (showLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(indicatorSize),
                color = indicatorColor,
                strokeWidth = strokeWidth
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    RFCardPlaceholder(
        height = 300.dp,
        padding = 16.dp,
    )
}
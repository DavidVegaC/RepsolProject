package com.repsol.tools.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun DisplayImage(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    contentDescription: String
) {
    Image(
        painter = painterResource(drawableRes),
        contentDescription = contentDescription,
        modifier = modifier
    )
}
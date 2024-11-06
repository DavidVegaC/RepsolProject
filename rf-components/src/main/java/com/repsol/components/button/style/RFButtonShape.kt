package com.repsol.components.button.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

enum class RFButtonShape {
    Regular,
    Round;

    fun getShape(): Shape {
        return when (this) {
            Regular -> RoundedCornerShape(4.dp)
            Round -> RoundedCornerShape(22.dp)
        }
    }
}

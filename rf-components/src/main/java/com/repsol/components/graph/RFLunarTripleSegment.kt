package com.repsol.components.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@Composable
fun RFLunarTripleSegment(
    active: Int,
    inactive: Int,
    canceled: Int,
    total: Int,
    date: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 40f // Grosor del arco
            val radius = (size.minDimension / 2) - strokeWidth // Radio del arco
            val centerX = size.width / 2
            val centerY = size.height / 2

            val totalAngle = 240f
            val totalData = active + inactive + canceled
            val activeAngle = totalAngle * (active / totalData.toFloat())
            val inactiveAngle = totalAngle * (inactive / totalData.toFloat())
            val canceledAngle = totalAngle * (canceled / totalData.toFloat())

            // Dibuja el segmento activo
            drawArc(
                color = RFColor.UxComponentColorGreen.color,
                startAngle = 150f, // Inicio del arco ajustado hacia la parte superior
                sweepAngle = activeAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(2 * radius, 2 * radius)
            )

            // Dibuja el segmento inactivo
            drawArc(
                color = RFColor.UxComponentColorGrey.color,
                startAngle = 150f + activeAngle,
                sweepAngle = inactiveAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(2 * radius, 2 * radius)
            )

            // Dibuja el segmento cancelado
            drawArc(
                color = RFColor.UxComponentColorRed.color,
                startAngle = 150f + activeAngle + inactiveAngle,
                sweepAngle = canceledAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
                topLeft = Offset(centerX - radius, centerY - radius),
                size = Size(2 * radius, 2 * radius)
            )
        }

        // Contenido en el centro (Total y Fecha)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RFText(
                text = "Total",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorCharcoal
                )

            )
            RFText(
                text = "$total",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 18.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )
            RFText(
                text = date,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorDarkGray
                )
            )
        }
    }
}
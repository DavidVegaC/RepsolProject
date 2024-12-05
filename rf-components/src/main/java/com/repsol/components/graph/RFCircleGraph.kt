package com.repsol.components.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.repsol.components.style.RFColor

@Composable
fun RFCircleGraph(
    approvedLine: Double, // Línea aprobada
    availableBalance: Double, // Saldo disponible
    commercialGoal: Int // Meta comercial (porcentaje)
) {
    Canvas(modifier = Modifier.size(200.dp)) {
        val strokeWidth = 30f
        val outerRadius = size.minDimension / 2 - strokeWidth
        val innerRadius = outerRadius - 40f // Ajustar según el espacio entre círculos

        // Validar proporciones
        val validatedAvailableBalance = if (availableBalance > approvedLine) approvedLine else availableBalance
        val usedBalancePercentage = ((approvedLine - validatedAvailableBalance) / approvedLine).toFloat()
        val availableBalancePercentage = (validatedAvailableBalance / approvedLine).toFloat()
        val commercialGoalPercentage = commercialGoal / 100f

        // Dibujar el anillo externo (Naranja y Azul)
        drawArc(
            color = RFColor.UxComponentColorDarkOrange.color, // Naranja (Saldo usado)
            startAngle = 0f,
            sweepAngle = usedBalancePercentage * 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            topLeft = Offset(strokeWidth, strokeWidth),
            size = Size(outerRadius * 2, outerRadius * 2)
        )
        drawArc(
            color = RFColor.UxComponentColorDarkCerulean.color, // Azul (Saldo disponible)
            startAngle = usedBalancePercentage * 360f, // Donde termina el anillo naranja
            sweepAngle = availableBalancePercentage * 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            topLeft = Offset(strokeWidth, strokeWidth),
            size = Size(outerRadius * 2, outerRadius * 2)
        )

        // Dibujar el fondo gris del anillo interno
        drawArc(
            color = RFColor.UxComponentColorWhiteSmoke.color, // Fondo gris
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            topLeft = Offset(40f + strokeWidth, 40f + strokeWidth),
            size = Size(innerRadius * 2, innerRadius * 2)
        )

        // Dibujar el anillo interno (Celeste - Progreso de la meta comercial)
        drawArc(
            color = RFColor.UxComponentColorIrisBlue.color, // Celeste
            startAngle = -90f,
            sweepAngle = commercialGoalPercentage * 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            topLeft = Offset(40f + strokeWidth, 40f + strokeWidth),
            size = Size(innerRadius * 2, innerRadius * 2)
        )
    }
}
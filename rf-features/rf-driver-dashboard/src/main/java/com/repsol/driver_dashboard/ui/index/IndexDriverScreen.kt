package com.repsol.driver_dashboard.ui.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.repsol.core_ui.stateful.Stateful

@Composable
fun IndexDriverScreen(modifier: Modifier = Modifier) = Stateful<IndexDriverViewModel> {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(16.dp)
    ) {
        Text("Index Screen")
    }
}
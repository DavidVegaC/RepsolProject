package com.repsol.components.dropdownmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RFDropdownMenu(
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf(options.firstOrNull() ?: "") }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RFColor.UxComponentColorWhite.color)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            RFText(
                                text = option,
                                textStyle = RFTextStyle.Roboto(
                                    fontSize = 16.sp,
                                    color = RFColor.UxComponentColorCharcoal
                                ))
                        },
                        onClick = {
                            selectedOption = option
                            expanded = false
                            onOptionSelected(option)
                        }
                    )
                }
            }
        }
    }
}
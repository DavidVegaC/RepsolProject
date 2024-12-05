package com.repsol.components.dropdownmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RFDropdownMenu(
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedOption ?: "",
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    RFText(
                        text = "Ejm. Centro de costo 01",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorDarkGray
                        )
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {expanded = !expanded}) {
                        RFIcon(
                            painter = painterResource(
                                id = if(expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                            ),
                            tint = if(expanded) RFColor.UxComponentColorBlueLagoon
                            else if (selectedOption != null) RFColor.UxComponentColorBlueLagoon
                            else RFColor.UxComponentColorGainsboro
                        )
                    }
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedContainerColor = RFColor.UxComponentColorWhite.color,
                    unfocusedContainerColor = RFColor.UxComponentColorWhite.color,
                    focusedBorderColor = RFColor.UxComponentColorGainsboro.color,
                    unfocusedBorderColor = RFColor.UxComponentColorGainsboro.color
                )
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .exposedDropdownSize()
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
                                )
                            )
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
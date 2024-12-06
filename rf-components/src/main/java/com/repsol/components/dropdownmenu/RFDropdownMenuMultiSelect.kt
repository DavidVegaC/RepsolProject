package com.repsol.components.dropdownmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.checkbox.RFCustomCheckbox
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_components.R

@Composable
fun RFDropdownMenuMultiSelect(
    options: List<String>,
    selectedOptions: List<String>,
    onSelectionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {

        Column(Modifier.fillMaxWidth()) {
            val displayedText = selectedOptions.joinToString()
            OutlinedTextField(
                value = displayedText,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                placeholder = {
                    RFText(
                        text = "Ejm. Centro de Costo 01",
                        textStyle = RFTextStyle.Roboto(
                            fontSize = 16.sp,
                            color = RFColor.UxComponentColorGainsboro
                        )
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        RFIcon(
                            painter = painterResource(
                                id = if (expanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
                            ),
                            tint = if (expanded) RFColor.UxComponentColorBlueLagoon
                            else if (displayedText.isEmpty()) RFColor.UxComponentColorBlueLagoon
                            else RFColor.UxComponentColorGainsboro
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = RFColor.UxComponentColorWhite.color,
                    unfocusedContainerColor = RFColor.UxComponentColorWhite.color,
                    focusedBorderColor = RFColor.UxComponentColorGainsboro.color.copy(0.7f),
                    unfocusedBorderColor = RFColor.UxComponentColorGainsboro.color.copy(0.7f)
                ),
                singleLine = true,
                maxLines = 1
            )

            if (expanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 4.dp)
                        .clip(RectangleShape)
                        .background(
                            RFColor.UxComponentColorWhite.color,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp)
                ) {

                    Column {

                        LazyColumn(
                            modifier = Modifier.heightIn(min = 200.dp, max = 250.dp)
                        ) {

                            items(options) { option ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {



                                    RFCustomCheckbox(
                                        checked = selectedOptions.contains(option),
                                        onCheckedChange = {
                                            onSelectionChange(option)
                                        },
                                        backgroundColor = RFColor.UxComponentColorDiamond,
                                        borderColor = RFColor.UxComponentColorIrisBlue,
                                        checkmarkColor = RFColor.UxComponentColorBlueLagoon

                                    )

                                    Spacer(Modifier.size(8.dp))

                                    RFText(
                                        text = option,
                                        textStyle = RFTextStyle.Roboto(
                                            fontSize = 16.sp,
                                            color = RFColor.UxComponentColorCharcoal
                                        )
                                    )
                                }

                                HorizontalDivider(color = RFColor.UxComponentColorLightGray.color)

                            }
                        }
                    }
                }
            }
        }


    }

}
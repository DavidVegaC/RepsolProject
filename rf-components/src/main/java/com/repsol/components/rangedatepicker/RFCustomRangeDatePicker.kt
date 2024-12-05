package com.repsol.components.rangedatepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.repsol.components.button.RFButton
import com.repsol.components.button.style.RFButtonOnColor
import com.repsol.components.card.RFCard
import com.repsol.components.card.RFCardStyle
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.rf_components.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun RFCustomDateRangePicker(
    startDate: Date?,
    endDate: Date?,
    onChangeValue: (Date, Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDropdown by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (textField, calendar) = createRefs()

        DatePickerTextField(
            startDate = startDate,
            endDate = endDate,
            onIconClick = { showDropdown = true },
            modifier = Modifier.constrainAs(textField) {
                top.linkTo(parent.top)
            }
        )

        if (showDropdown) {
            DateRangePickerContent(
                onCancel = { showDropdown = false },
                onAccept = { start, end ->
                    onChangeValue(start, end)
                    showDropdown = false
                },
                modifier = Modifier.constrainAs(calendar) {
                    top.linkTo(textField.bottom)
                }
            )
        }
    }
}

@Composable
fun DatePickerTextField(
    startDate: Date?,
    endDate: Date?,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es"))
    val displayText = if (startDate != null && endDate != null) {
        "${dateFormat.format(startDate)} - ${dateFormat.format(endDate)}"
    } else {
        "dd/mm/aaaa - dd/mm/aaaa"
    }

    val iconColor =
        if (startDate != null && endDate != null) RFColor.UxComponentColorEasternBlue else RFColor.UxComponentColorDarkGray

    val texColor =
        if (startDate != null && endDate != null) RFColor.UxComponentColorCharcoal else RFColor.UxComponentColorDarkGray


    OutlinedTextField(
        value = displayText,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = onIconClick) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_calendar),
                    tint = iconColor
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        enabled = false,
        colors = TextFieldDefaults.colors(
            disabledTextColor = texColor.color,
            disabledContainerColor = RFColor.UxComponentColorWhite.color,
            disabledIndicatorColor = RFColor.UxComponentColorGainsboro.color
        )
    )
}

@Composable
fun DateRangePickerContent(
    onCancel: () -> Unit,
    onAccept: (Date, Date) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = Calendar.getInstance()
    var selectedStartDate by remember { mutableStateOf<Date?>(null) }
    var selectedEndDate by remember { mutableStateOf<Date?>(null) }
    var displayedMonth by remember { mutableStateOf(today) }

    RFCard(
        modifier = modifier
            .wrapContentHeight()
            .background(RFColor.UxComponentColorWhite.color),
        shape = RoundedCornerShape(8.dp),
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorWhite,
        clickable = false
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CalendarHeader(
                displayedMonth = displayedMonth,
                onPreviousMonth = {
                    displayedMonth = (displayedMonth.clone() as Calendar).apply { add(Calendar.MONTH, -1) }
                },
                onNextMonth = {
                    displayedMonth = (displayedMonth.clone() as Calendar).apply { add(Calendar.MONTH, 1) }
                }
            )

            CalendarDaysOfWeek()

            CalendarDays(
                displayedMonth = displayedMonth,
                selectedStartDate = selectedStartDate,
                selectedEndDate = selectedEndDate,
                onDateClick = { date ->
                    if (selectedStartDate == null || selectedEndDate != null) {
                        selectedStartDate = date
                        selectedEndDate = null
                    } else {
                        selectedEndDate = if (date.before(selectedStartDate)) {
                            selectedStartDate.also { selectedStartDate = date }
                        } else date
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            CalendarActions(
                onCancel = onCancel,
                onAccept = {
                    if (selectedStartDate != null && selectedEndDate != null) {
                        onAccept(selectedStartDate!!, selectedEndDate!!)
                    }
                },
                isAcceptEnabled = selectedStartDate != null && selectedEndDate != null
            )
        }
    }
}

@Composable
fun CalendarActions(
    onCancel: () -> Unit,
    onAccept: () -> Unit,
    isAcceptEnabled: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RFButton(
            modifier = Modifier.height(48.dp),
            text = "Cancelar",
            textStyle = RFTextStyle.Roboto(
                fontSize = 16.sp,
                color = RFColor.UxComponentColorCharcoal
            ),
            onClick = onCancel,
            rfOnColor = RFButtonOnColor.Primary,
        )

        RFButton(
            modifier = Modifier.height(48.dp),
            onClick = onAccept,
            text = "Aceptar",
            textStyle = RFTextStyle.Roboto(
                fontSize = 16.sp,
                color = if(isAcceptEnabled) RFColor.UxComponentColorDarkOrange else RFColor.UxComponentColorCharcoal
            ),
            rfOnColor = RFButtonOnColor.Primary
        )
    }
}

@Composable
fun CalendarDays(
    displayedMonth: Calendar,
    selectedStartDate: Date?,
    selectedEndDate: Date?,
    onDateClick: (Date) -> Unit
) {
    val daysInMonth = displayedMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfWeek =
        displayedMonth.apply { set(Calendar.DAY_OF_MONTH, 1) }.get(Calendar.DAY_OF_WEEK) - 1
    val days = (1..daysInMonth).toList()
    val gridItems = List(firstDayOfWeek) { null } + days.map { it }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        gridItems.forEach { day ->
            item {
                if (day != null) {
                    val currentDate = displayedMonth.clone() as Calendar
                    currentDate.set(Calendar.DAY_OF_MONTH, day)
                    val currentTime = currentDate.time

                    val isSelected =
                        currentTime == selectedStartDate || currentTime == selectedEndDate
                    val isInRange = selectedStartDate != null && selectedEndDate != null &&
                            currentTime.after(selectedStartDate) && currentTime.before(
                        selectedEndDate
                    )

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                when {
                                    isSelected -> RFColor.UxComponentColorSafetyOrange.color
                                    isInRange -> RFColor.UxComponentColorSafetyOrange.color.copy(
                                        alpha = 0.3f
                                    )

                                    else -> Color.Transparent
                                },
                                shape = RectangleShape
                            )
                            .clickable { onDateClick(currentTime) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.toString(),
                            color = if (isSelected) RFColor.UxComponentColorWhite.color
                            else if (isInRange) RFColor.UxComponentColorCharcoal.color
                            else RFColor.UxComponentColorCharcoal.color
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarHeader(
    displayedMonth: Calendar,
    onPreviousMonth: () -> Unit,
    onNextMonth: () -> Unit
) {
    val monthYear = SimpleDateFormat("MMMM yyyy", Locale("es")).format(displayedMonth.time)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    RFColor.UxComponentColorPowderBlue.color,
                )
        ) {
            IconButton(onClick = onPreviousMonth) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    tint = RFColor.UxComponentColorBlueLagoon
                )
            }
        }

        RFText(
            text = monthYear.replaceFirstChar { it.uppercase() },
            textStyle = RFTextStyle.Roboto(
                fontSize = 16.sp,
                color = RFColor.UxComponentColorCharcoal
            )
        )

        Box(
            Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    RFColor.UxComponentColorPowderBlue.color,
                )
        ) {
            IconButton(onClick = onNextMonth) {
                RFIcon(
                    painter = painterResource(R.drawable.ic_arrow_right),
                    tint = RFColor.UxComponentColorBlueLagoon
                )
            }
        }

    }
}

@Composable
fun CalendarDaysOfWeek() {
    val daysOfWeek = remember {
        val firstDayOfWeek = Calendar.getInstance().firstDayOfWeek
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek)

        for (i in 0..6) {
            val dayName = SimpleDateFormat("E", Locale("es")).format(calendar.time)
            days.add(dayName.substring(0, 1).uppercase())
            calendar.add(Calendar.DAY_OF_WEEK, 1)
        }
        days
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp), horizontalArrangement = Arrangement.SpaceAround
    ) {
        daysOfWeek.forEach { days ->
            RFText(
                text = days.uppercase(),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorNickel
                ),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}
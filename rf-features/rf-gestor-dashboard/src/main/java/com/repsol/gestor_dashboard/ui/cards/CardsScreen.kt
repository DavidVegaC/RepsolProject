package com.repsol.gestor_dashboard.ui.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.components.card.RFItemCard
import com.repsol.components.filter.RFFilter
import com.repsol.components.graph.RFLunarTripleSegment
import com.repsol.components.icon.RFIcon
import com.repsol.components.multiselectbutton.MultiSelectButton
import com.repsol.components.placeholder.RFCardError
import com.repsol.components.placeholder.RFCardPlaceholder
import com.repsol.components.quickactioncard.RFQuickActionCard
import com.repsol.components.searchbar.RFCustomSearchBar
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFSpannableText
import com.repsol.components.text.RFText
import com.repsol.core_domain.common.entities.ServiceStatus
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.rf_assets.R
import com.repsol.tools.components.ReusableSpacer
import com.repsol.gestor_dashboard.ui.cards.interactor.CardsUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.cards.interactor.CardsUiState as UiState

@Composable
fun CardsScreen(modifier: Modifier = Modifier) = Stateful<CardsViewModel> {

    val uiState by uiState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(410.dp)
                        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    RFColor.UxComponentColorMidnightBlue.color,
                                    RFColor.UxComponentColorCerulean.color,
                                    RFColor.UxComponentColorCGBlue.color
                                )
                            )
                        )
                ) {
                    Column(
                        Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                    ) {
                        HeaderSection()
                        ReusableSpacer(24.dp)
                        when (uiState.kpiStatus) {
                            ServiceStatus.BLANK -> {
                                RFCardPlaceholder(
                                    height = 200.dp,
                                    showLoading = false,
                                )
                            }

                            ServiceStatus.LOADING -> {
                                RFCardPlaceholder(
                                    height = 200.dp,
                                )
                            }

                            ServiceStatus.SUCCESS -> {
                                InfoSection()
                            }

                            ServiceStatus.ERROR -> {
                                InfoSectionError()
                            }
                        }

                    }
                }
            }
            item {
                Box(
                    Modifier.layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        val offset = 100.dp.roundToPx().coerceAtMost(placeable.height)
                        val newHeight = (placeable.height - offset).coerceAtLeast(0)
                        layout(placeable.width, newHeight) {
                            placeable.placeRelative(0, -offset)
                        }
                    }
                ) {

                    Column(Modifier.fillMaxWidth()) {
                        Options()
                        ReusableSpacer(24.dp)
                        SearchBarCustom()
                        Filter()
                    }
                }
            }

            item {
                HeaderCardList()
            }
            items(uiState.cards) { card ->
                RFItemCard(
                    title = stringResource(R.string.card_value, card.cardNumber),
                    subtitle = card.numberPlate,
                    typeKey = stringResource(R.string.type),
                    valueKey = card.featureDescription,
                    icon = R.drawable.ic_card_pass,
                )
            }
            item {
                FooterPagination()
            }
        }
    }
}

@Composable
private fun HeaderSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RFIcon(
                painter = painterResource(R.drawable.ic_card_pass),
                tint = RFColor.UxComponentColorDarkOrange
            )

            ReusableSpacer(8.dp)

            RFText(
                text = stringResource(R.string.my_cards),
                textStyle = RFTextStyle.RobotoMedium(
                    fontSize = 20.sp,
                    color = RFColor.UxComponentColorWhite
                )
            )
        }
    }
}

@Composable
private fun InfoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(200.dp)
            .background(RFColor.UxComponentColorWhite.color, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            ContentTextAndGraph()
        }
    }
}

@Composable
private fun ContentTextAndGraph() = ChildStateful<CardsViewModel> {

    val uiState by uiState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            KpiItem(RFColor.UxComponentColorGreen, stringResource(R.string.active), uiState.active)

            ReusableSpacer(12.dp)

            KpiItem(RFColor.UxComponentColorGainsboro, stringResource(R.string.inactive), uiState.inactive)

            ReusableSpacer(12.dp)

            KpiItem(RFColor.UxComponentColorRed, stringResource(R.string.canceled), uiState.canceled)
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(start = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            RFLunarTripleSegment(
                active = uiState.active,
                inactive = uiState.inactive,
                canceled = uiState.canceled,
            )
        }
    }
}

@Composable
private fun KpiItem(rfColor: RFColor, title: String, count: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .heightIn(48.dp)
                .background(
                    rfColor.color,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        ReusableSpacer(8.dp)

        Column {
            RFText(
                text = title,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 12.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            RFText(
                text = "$count",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 18.sp,
                    color = rfColor
                )
            )
        }

    }
}

@Composable
fun InfoSectionError() {
    RFCardError(
        R.drawable.image_home_error,
        stringResource(R.string.view_not_available_at_this_time),
        stringResource(R.string.retry),
        onClick = { }
    )
}

@Composable
private fun Options() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        RFIcon(
            painter = painterResource(R.drawable.ic_save),
            tint = RFColor.UxComponentColorDarkOrange

        )

        ReusableSpacer(8.dp)

        RFText(
            text = stringResource(R.string.what_do_you_want_to_do),
            textStyle = RFTextStyle.RobotoMedium(
                fontSize = 20.sp,
                color = RFColor.UxComponentColorWhite
            )
        )

    }

    ReusableSpacer(24.dp)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items = uiState.actionCards, key = { it.id }) { card ->
            RFQuickActionCard(
                icon = painterResource(card.icon),
                text = stringResource(card.title),
                onClick = card.onClick,
            )
        }
    }
}

@Composable
private fun SearchBarCustom() = ChildStateful<CardsViewModel> {

    val uiState by uiState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        RFIcon(
            painter = painterResource(R.drawable.ic_menu_filled),
            tint = RFColor.UxComponentColorDarkOrange

        )

        ReusableSpacer(8.dp)

        RFText(
            text = stringResource(R.string.list_general),
            textStyle = RFTextStyle.RobotoMedium(
                fontSize = 20.sp,
                color = RFColor.UxComponentColorCharcoal
            )
        )

    }

    RFCustomSearchBar(
        modifier = Modifier.padding(16.dp),
        query = uiState.searchText,
        onQueryChange = { execUiIntent(UiIntent.UpdateSearchText(it)) },
        onSearchClick = {/*Accion de busqueda*/ },
        icon = R.drawable.ic_search
    )
}

@Composable
private fun MultiSelectedGroup() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        MultiSelectButton(
            options = uiState.totalOptions,
            selectedOptions = uiState.selectedOptions,
            onOptionSelected = { execUiIntent(UiIntent.AddSelectedOption(it))},
            onOptionDeselected = { execUiIntent(UiIntent.RemoveSelectedOption(it)) },
            leadingIcon = R.drawable.ic_check
        )
    }
}

@Composable
private fun Filter() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        RFFilter(
            radius = 24.dp,
            leadingContent = {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = RFColor.UxComponentColorDiamond.color,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    RFIcon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ic_filter),
                        tint = RFColor.UxComponentColorBlueLagoon
                    )
                }
            },
            textContent = {
                RFText(
                    text = stringResource(R.string.filter),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 14.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )
            }

        )
    }
}

@Composable
private fun HeaderCardList() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    if (uiState.cards.isNotEmpty() && uiState.totalRows != 0) {

        val displayCountList = stringResource(R.string.display_part_of_total_cards, uiState.cards.size, uiState.totalRows)
        val displayHeaderTotalList = stringResource(R.string.display_register_cards, displayCountList)

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            RFText(
                text = displayHeaderTotalList,
                textStyle = RFTextStyle.Roboto(
                    fontSize = 14.sp,
                    color = RFColor.UxComponentColorCharcoal
                ),
                highlight = RFSpannableText(
                    text = displayCountList,
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 14.sp,
                        color = RFColor.UxComponentColorCharcoal,
                    ),
                    onClick = {}
                )
            )
        }
    }
}

@Composable
private fun FooterPagination() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    if (uiState.cards.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ItemPagination(uiState.isEnabledPreviousPaginate, R.drawable.ic_left_paginate) {
                execUiIntent(UiIntent.LoadPreviousPaginate)
            }

            ReusableSpacer(16.dp)

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        RFColor.UxComponentColorWhite.color,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                RFText(
                    text = "${uiState.currentPage}",
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )
            }

            ReusableSpacer(16.dp)

            ItemPagination(uiState.isEnabledNextPaginate, R.drawable.ic_right_paginate) {
                execUiIntent(UiIntent.LoadNextPaginate)
            }
        }
    }
}

@Composable
private fun ItemPagination(
    isEnabled: Boolean,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .background(
                color = if (isEnabled) RFColor.UxComponentColorPowderBlue.color else RFColor.UxComponentColorLightGray.color,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(enabled = isEnabled) {
                if (isEnabled) onClick.invoke()
            }
    ) {
        RFIcon(
            painter = painterResource(icon),
            tint = if (isEnabled) RFColor.UxComponentColorBlueLagoon else RFColor.UxComponentColorWhite
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultCardsPreview() {
    ScreenPreview(uiState = UiState()) {
        CardsScreen()
    }
}
package com.repsol.gestor_dashboard.ui.cards.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.repsol.components.card.RFCard
import com.repsol.components.card.RFCardStyle
import com.repsol.components.card.RFItemCard
import com.repsol.components.quickactioncard.RFQuickActionCard
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFSpannableText
import com.repsol.components.text.RFText
import com.repsol.components.topbar.RFTopBar
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.gestor_dashboard.domain.entity.CardItem
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer
import com.repsol.tools.utils.EMPTY_STRING
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiEvent as UiEvent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiIntent as UiIntent
import com.repsol.gestor_dashboard.ui.cards.detail.interactor.DetailCardUiState as UiState

@Composable
fun DetailCardScreen(navController: NavHostController, modifier: Modifier = Modifier) = Stateful<DetailCardViewModel> {

    OnUiEvent {
        when (it) {
            UiEvent.NavigationToBack -> navController.popBackStack()
        }
    }


    Scaffold(
        topBar = {
            RFTopBar(
                title = stringResource(R.string.title_detail_cards),
                iconDefault = painterResource(R.drawable.ic_arrow_left),
                onBackClick = {
                    execUiIntent(UiIntent.NavigationBack)
                }
            )
        },
        content = { paddingValues ->
            MainContent(modifier.padding(top = paddingValues.calculateTopPadding()))
        }
    )
}

@Composable
private fun MainContent(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(RFColor.UxComponentColorWhiteSmoke.color)
    ) {

        Column {
            HeaderDetail()
            QuickActionCardList()
            HorizontalDivider(Modifier.padding(vertical = 16.dp), color = RFColor.UxComponentColorGainsboro.color)
            StopsAndControl()
            AvailableBalance()
            BusinessRule()
            PhysicalCardAndControlKM()
            DetailVehicle()
        }
    }
}

@Composable
private fun DetailVehicle() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()

    RFCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorTransparent,
        clickable = false,
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            DisplayImage(
                modifier = Modifier
                    .align(Alignment.Start),
                R.drawable.truck
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                text = uiState.item.numberPlate,
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = "Mercedez Benz",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = "Axor 2041 LS",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = "Tracto-Camion",
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                )
            )
        }
    }
}

@Composable
private fun HeaderDetail() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()

    val title: String = if (uiState.isDriver) stringResource(R.string.double_string, uiState.item.typeDocumentDescription, uiState.item.numberDocument)
    else stringResource(R.string.card_value, uiState.item.cardNumber)

    val subTitle: String = if (uiState.isDriver) uiState.item.driverName else uiState.item.numberPlate

    val keyValue: String = if (uiState.isDriver) EMPTY_STRING else uiState.item.featureDescription

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(RFColor.UxComponentColorSafetyOrange.color)
            .padding(24.dp)
    ) {

        Column {
            RFText(
                text = stringResource(R.string.title_detail_cards),
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorWhite
                )
            )

            ReusableSpacer(16.dp)

            RFItemCard(
                modifier = Modifier.fillMaxWidth(),
                title = title,
                subtitle = subTitle,
                typeKey = stringResource(R.string.type),
                valueKey = keyValue,
                icon = R.drawable.ic_card_pass,
                isClickable = false,
                idStatus = uiState.item.statusCode,
            )
        }
    }
}

@Composable
private fun QuickActionCardList() = Stateful<DetailCardViewModel> {

    val uiState by uiState()

    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        uiState.actionCards.forEach { card ->
            RFQuickActionCard(
                modifier = Modifier.size(size = 80.dp),
                icon = card.icon,
                text = stringResource(card.title),
                onClick = card.onClick,
            )
            if (card != uiState.actionCards.last()) {
                ReusableSpacer(16.dp)
            }
        }
    }
}


@Composable
private fun StopsAndControl() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()

    RFCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorTransparent,
        clickable = false
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            DisplayImage(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Start),
                R.drawable.card_stops_control
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = stringResource(R.string.cards_stops_control),
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = stringResource(R.string.stops_days, uiState.item.descriptionControlType),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                ),
                highlight = RFSpannableText(
                    text = uiState.item.descriptionControlType,
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorBlueLagoon,
                    ),
                    onClick = {}
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = stringResource(R.string.tope_type, uiState.item.descriptionResetAmount.lowercase()),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                ),
                highlight = RFSpannableText(
                    text = uiState.item.descriptionResetAmount.lowercase(),
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorBlueLagoon,
                    ),
                    onClick = {}
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = stringResource(R.string.tope_description, uiState.item.maxAmount.lowercase()),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorBlueLagoon
                ),
                highlight = RFSpannableText(
                    text = uiState.item.maxAmount.lowercase(),
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorBlueLagoon,
                    ),
                    onClick = {}
                )
            )
        }
    }
}

@Composable
private fun AvailableBalance() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()
    RFCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp),
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorTransparent,
        clickable = false,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.available_balance),
                textStyle = RFTextStyle.RobotoBold(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                text = uiState.item.actAmount,
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )
        }
    }
}

@Composable
private fun BusinessRule() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()

    RFCard(
        Modifier
            .fillMaxWidth()
            .heightIn(120.dp)
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp),
        style = RFCardStyle.Outline,
        borderColor = RFColor.UxComponentColorTransparent,
        clickable = false,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            RFText(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.cards_business_rule),
                textStyle = RFTextStyle.RobotoBold(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorCharcoal
                )
            )

            RFText(
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                text = uiState.item.descriptionBusinessRule,
                textStyle = RFTextStyle.Repsol(
                    fontSize = 28.sp,
                    color = RFColor.UxComponentColorDarkOrange
                )
            )
        }
    }
}

@Composable
private fun PhysicalCardAndControlKM() = ChildStateful<DetailCardViewModel> {
    val uiState by uiState()
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        RFCard(
            Modifier.weight(1f).fillMaxHeight(),
            style = RFCardStyle.Outline,
            borderColor = RFColor.UxComponentColorTransparent,
            clickable = false,
        ) {
            Column(Modifier.fillMaxHeight().padding(20.dp)) {
                RFText(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.cards_physical),
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                RFText(
                    modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                    text = uiState.item.descriptionStateRequestPhysicalCard,
                    textStyle = RFTextStyle.Repsol(
                        fontSize = 28.sp,
                        color = RFColor.UxComponentColorDarkOrange
                    )
                )
            }

        }

        RFCard(
            Modifier.weight(1f).fillMaxHeight(),
            style = RFCardStyle.Outline,
            borderColor = RFColor.UxComponentColorTransparent,
            clickable = false,
        ) {
            Column(Modifier.padding(20.dp)) {
                RFText(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    text = stringResource(R.string.cards_control_km),
                    textStyle = RFTextStyle.RobotoBold(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorCharcoal
                    )
                )

                RFText(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    text = uiState.item.hasControlKm,
                    textStyle = RFTextStyle.Repsol(
                        fontSize = 28.sp,
                        color = RFColor.UxComponentColorDarkOrange
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val cardItem = CardItem(
        creationDate = "",
        descriptionState = "",
        descriptionCenterCost = "",
        featureDescription = "",
        descriptionControlType = "",
        codeFeaturesCard = "",
        typeDocumentDescription = "",
        numberDocument = "",
        cardNumber = "",
        driverName = "",
        maxAmount = "",
        actAmount = "",
        numberPlate = "",
        statusCode = "",
        descriptionBusinessRule = "",
        descriptionStateRequestPhysicalCard = "",
        hasControlKm = "",
        descriptionResetAmount = "",
    )
    ScreenPreview(uiState = UiState(cardItem)) {
        DetailCardScreen(rememberNavController())
    }
}

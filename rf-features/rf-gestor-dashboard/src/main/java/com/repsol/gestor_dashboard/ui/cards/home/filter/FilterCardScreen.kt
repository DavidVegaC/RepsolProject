package com.repsol.gestor_dashboard.ui.cards.home.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.repsol.components.button.RFButton
import com.repsol.components.button.style.RFButtonOnColor
import com.repsol.components.dropdownmenu.RFDropdownMenu
import com.repsol.components.dropdownmenu.RFDropdownMenuMultiSelect
import com.repsol.components.icon.RFIcon
import com.repsol.components.multiselectbutton.MultiSelectButton
import com.repsol.components.rangedatepicker.RFCustomDateRangePicker
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFClickableText
import com.repsol.components.text.RFText
import com.repsol.components.topbar.RFTopBar
import com.repsol.core_domain.common.entities.AttentionType
import com.repsol.core_domain.common.entities.CardFeature
import com.repsol.core_domain.common.entities.CardState
import com.repsol.core_domain.common.entities.CostCenter
import com.repsol.core_domain.common.entities.DocumentType
import com.repsol.core_domain.common.entities.MileageStatus
import com.repsol.core_domain.common.entities.PhysicalCardState
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.Stateful
import com.repsol.gestor_dashboard.ui.cards.home.CardsViewModel
import com.repsol.gestor_dashboard.ui.cards.home.interactor.CardsUiIntent
import com.repsol.rf_assets.R
import com.repsol.tools.components.ReusableSpacer
import com.repsol.gestor_dashboard.ui.cards.home.interactor.CardsUiIntent as UiIntent

@Composable
fun FilterCardsScreen(
    navController: NavHostController,
    viewModel: CardsViewModel,
) = Stateful(handlerFactory = { viewModel }) {

    Scaffold(
        topBar = {
            RFTopBar(
                title = stringResource(R.string.top_bar_filter_cards_title),
                iconDefault = painterResource(R.drawable.ic_arrow_left),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        },
        content = { innerPadding ->
            MainContent(modifier = Modifier.padding(innerPadding))
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
            AddOptionsCardFeature()
            AddRangeCreationDate()
            AddOptionsCardState()
            AddDriverName()
            AddOptionsDocumentType()
            AddOptionsAttentionType()
            CardCostCenter()
            AddOptionsPhysicalCardState()
            AddOptionsMileageStatus()
            CardFilterControlButton()
        }

    }

}

@Composable
private fun CardFilterControlButton() = ChildStateful<CardsViewModel> {
    val uiState by uiState()
    Box(
        Modifier.fillMaxSize()
            .padding(bottom = 24.dp, top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RFButton(
                text = stringResource(R.string.aplly_filter_card),
                onClick = { execUiIntent(UiIntent.ApplyFilters) },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(48.dp),
                isLoading = uiState.isLoadingFilter,
            )

            RFButton(
                text = stringResource(R.string.clear_filter_card),
                onClick = { execUiIntent(UiIntent.CleanFilters) },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 8.dp)
                    .height(48.dp),
                rfOnColor = RFButtonOnColor.Primary,
            )
        }

    }
}

@Composable
private fun CardCostCenter() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.cost_center_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )
        }

        ReusableSpacer(4.dp)

        RFDropdownMenuMultiSelect(
            options = uiState.costCenter.map { it.description },
            selectedOptions = uiState.selectedCostCenter.map { it.description },
            onSelectionChange = { option ->
                    val costCenter = uiState.costCenter.find { it.description == option }
                    costCenter?.let {
                        execUiIntent(UiIntent.AddSelectedCostCenter(it))
                    }
            }
        )
    }
}

@Composable
private fun AddOptionsCardFeature() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.card_type),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllCardFeatureOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.cardFeatures.map { it.description },
            selectedOptions = uiState.selectedCardFeatures.map { it.description },
            onOptionSelected = { option ->
                val cardFeature: CardFeature? = uiState.cardFeatures.find { it.description == option }
                cardFeature?.let{ execUiIntent(UiIntent.AddSelectedCardFeature(it)) }
            },
            onOptionDeselected = { option ->
                val cardFeature: CardFeature? = uiState.cardFeatures.find { it.description == option }
                cardFeature?.let{ execUiIntent(UiIntent.RemoveSelectedCardFeature(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )
    }
}

@Composable
private fun AddRangeCreationDate() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.creation_date_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )
        }

        RFCustomDateRangePicker(
            modifier = Modifier.padding(top = 12.dp),
            startDate = uiState.startDate,
            endDate = uiState.endDate,
            onChangeValue = { startDate, endDate -> execUiIntent(UiIntent.OnChangeValueForDateRange(startDate, endDate)) },
        )
    }
}


@Composable
private fun AddOptionsCardState() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.card_status_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllCardStateOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.cardStates.map { it.description },
            selectedOptions = uiState.selectedCardStates.map { it.description },
            onOptionSelected = { option ->
                val cardState: CardState? = uiState.cardStates.find { it.description == option }
                cardState?.let{ execUiIntent(UiIntent.AddSelectedCardState(it)) }
            },
            onOptionDeselected = { option ->
                val cardState: CardState? = uiState.cardStates.find { it.description == option }
                cardState?.let{ execUiIntent(UiIntent.RemoveSelectedCardState(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )
    }
}

@Composable
private fun AddDriverName() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        RFText(
            text = stringResource(R.string.driver_card),
            textStyle = RFTextStyle.Roboto(
                fontSize = 16.sp,
                color = RFColor.UxComponentColorGrey
            )
        )

        OutlinedTextField(
            value = uiState.driverName,
            onValueChange = { execUiIntent(UiIntent.UpdateDriverName(it)) },
            placeholder = {
                RFText(
                    text = stringResource(R.string.placeholder_driver_card),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorDarkGray
                    ),
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                if (isFocused) {
                    IconButton(onClick = {}) {
                        RFIcon(
                            painter = painterResource(R.drawable.ic_clear)
                        )
                    }
                }
            },
            textStyle = TextStyle(
                textDecoration = TextDecoration.None
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            colors = TextFieldDefaults.colors(
                //Contenedor
                focusedContainerColor = RFColor.UxComponentColorWhite.color,
                unfocusedContainerColor = RFColor.UxComponentColorWhite.color,

                //Borde
                focusedIndicatorColor = RFColor.UxComponentColorGainsboro.color,
                unfocusedIndicatorColor = RFColor.UxComponentColorGainsboro.color,
            )
        )
    }
}

@Composable
private fun AddOptionsDocumentType() = ChildStateful<CardsViewModel> {
    val uiState by uiState()
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.document_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllDocumentTypeOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.documentTypes.map { it.description },
            selectedOptions = uiState.selectedDocumentTypes.map { it.description },
            onOptionSelected = { option ->
                val documentType: DocumentType? = uiState.documentTypes.find { it.description == option }
                documentType?.let{ execUiIntent(UiIntent.AddSelectedDocumentType(it)) }
            },
            onOptionDeselected = { option ->
                val documentType: DocumentType? = uiState.documentTypes.find { it.description == option }
                documentType?.let{ execUiIntent(UiIntent.RemoveSelectedDocumentType(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )

        RFText(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.number_document_card),
            textStyle = RFTextStyle.Roboto(
                fontSize = 12.sp,
                color = RFColor.UxComponentColorCharcoal
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = uiState.numberDocument,
            onValueChange = { execUiIntent(UiIntent.UpdateDocumentNumber(it)) },
            placeholder = {
                RFText(
                    text = stringResource(R.string.placeholder_document_card),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorDarkGray
                    )
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                textDecoration = TextDecoration.None
            ),
            colors = TextFieldDefaults.colors(
                //Contenedor
                focusedContainerColor = RFColor.UxComponentColorWhite.color,
                unfocusedContainerColor = RFColor.UxComponentColorWhite.color,

                //Borde
                focusedIndicatorColor = RFColor.UxComponentColorGainsboro.color,
                unfocusedIndicatorColor = RFColor.UxComponentColorGainsboro.color,
            )
        )
    }
}

@Composable
private fun AddOptionsAttentionType() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.attention_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllAttentionTypeOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.attentionTypes.map { it.description },
            selectedOptions = uiState.selectedAttentionTypes.map { it.description },
            onOptionSelected = { option ->
                val attentionType: AttentionType? = uiState.attentionTypes.find { it.description == option }
                attentionType?.let{ execUiIntent(UiIntent.AddSelectedAttentionType(it)) }
            },
            onOptionDeselected = { option ->
                val attentionType: AttentionType? = uiState.attentionTypes.find { it.description == option }
                attentionType?.let{ execUiIntent(UiIntent.RemoveSelectedAttentionType(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )
    }
}

@Composable
private fun AddOptionsPhysicalCardState() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.physical_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllPhysicalCardStateOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.physicalCardStates.map { it.description },
            selectedOptions = uiState.selectedPhysicalCardStates.map { it.description },
            onOptionSelected = { option ->
                val physicalCardState: PhysicalCardState? = uiState.physicalCardStates.find { it.description == option }
                physicalCardState?.let{ execUiIntent(UiIntent.AddSelectedPhysicalCardState(it)) }
            },
            onOptionDeselected = { option ->
                val physicalCardState: PhysicalCardState? = uiState.physicalCardStates.find { it.description == option }
                physicalCardState?.let{ execUiIntent(UiIntent.RemoveSelectedPhysicalCardState(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )
    }
}


@Composable
private fun AddOptionsMileageStatus() = ChildStateful<CardsViewModel> {
    val uiState by uiState()

    Column(Modifier.padding(horizontal = 24.dp).padding(top = 24.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RFText(
                text = stringResource(R.string.controlkm_card),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorGrey
                )
            )

            RFClickableText(
                text = stringResource(R.string.all),
                onClick = { execUiIntent(UiIntent.SelectAllMileageStatusOption) },
            )
        }

        MultiSelectButton(
            modifier = Modifier.padding(top = 12.dp),
            options = uiState.mileageStatus.map { it.description },
            selectedOptions = uiState.selectedMileageStatus.map { it.description },
            onOptionSelected = { option ->
                val mileageStatus: MileageStatus? = uiState.mileageStatus.find { it.description == option }
                mileageStatus?.let{ execUiIntent(UiIntent.AddSelectedMileageStatus(it)) }
            },
            onOptionDeselected = { option ->
                val mileageStatus: MileageStatus? = uiState.mileageStatus.find { it.description == option }
                mileageStatus?.let{ execUiIntent(UiIntent.RemoveSelectedMileageStatus(it)) }
            },
            leadingIcon = R.drawable.ic_check
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    FilterCardsScreen(rememberNavController(), hiltViewModel())
}
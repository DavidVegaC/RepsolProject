package com.repsol.auth.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.auth.data.model.OnboardingPage
import com.repsol.auth.ui.AuthGraph
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiState
import com.repsol.components.button.RFButton
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.navigation.core.localNavController
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacer
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiEvent as UiEvent
import com.repsol.auth.ui.onboarding.interactor.OnboardingUiIntent as UiIntent

@Composable
fun OnboardingScreen() = Stateful<OnboardingViewModel> {
    val navController = localNavController()
    val uiState by uiState()
    OnUiEvent {
        when (it) {
            is UiEvent.GoToLogin -> {
                navController.navigate(AuthGraph.Login)
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingLogo(Modifier.align(Alignment.CenterHorizontally))
        OnboardingContent(Modifier.align(Alignment.CenterHorizontally),
            uiState = uiState,
            onSkip = { execUiIntent(UiIntent.Skip) },
            onNext = { execUiIntent(UiIntent.Next) },
            onStart = { execUiIntent(UiIntent.Start) },
            updateCurrentPage = { page -> execUiIntent(UiIntent.UpdateCurrentPage(page)) }
        )
    }
}

@Composable
fun OnboardingContent(
    modifier: Modifier,
    uiState: OnboardingUiState,
    onSkip: () -> Unit,
    onNext: () -> Unit,
    onStart: () -> Unit,
    updateCurrentPage: (Int) -> Unit
) {
    val pagerState = rememberPagerState(OnboardingUiState.INITIAL_PAGE) { uiState.pages.size }
    val isLastPage = pagerState.currentPage == uiState.pages.size - 1

    LaunchedEffect(uiState.currentPage) {
        if (pagerState.currentPage != uiState.currentPage) {
            pagerState.animateScrollToPage(uiState.currentPage)
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            updateCurrentPage(it)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.height(350.dp),
    ) {
        val onboardingPage = uiState.pages[it]
        Box(
            modifier = modifier
                .padding(10.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            OnboardingPageContent(onboardingPage)
        }

    }


    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) RFColor.UxComponentColorSafetyOrange.color else RFColor.UxComponentColorWhite.color
            Box(
                modifier = modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }

    }

    if (isLastPage) {
        RFButton(
            modifier = modifier.fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.start),
            onClick = onStart
        )

    } else {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = onSkip,
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(48.dp)
                    .padding(start = 24.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                RFText(text = stringResource(R.string.skip),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp
                    ))
            }

            RFButton(
                modifier = modifier.fillMaxWidth()
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 24.dp),
                text = stringResource(R.string.next),
                onClick = onNext
            )

        }
    }


}

@Composable
fun OnboardingPageContent(onboardingPage: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        RFText(
            text = stringResource(onboardingPage.titleRes),
            textStyle = RFTextStyle.Repsol(
                color = RFColor.UxComponentColorSafetyOrange,
                fontSize = 28.sp
            ),
            textAlign = TextAlign.Center
        )

        ReusableSpacer(24.dp)

        Image(
            painter = painterResource(onboardingPage.imageRes),
            contentDescription = stringResource(R.string.onboarding_content_description_carousel)
        )

        ReusableSpacer(24.dp)

        RFText(
            text = stringResource(onboardingPage.descriptionRes),
            textStyle = RFTextStyle.Roboto(
                color = RFColor.UxComponentColorGrey,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun OnboardingLogo(modifier: Modifier) {
    DisplayImage(
        modifier = modifier.padding(48.dp),
        R.drawable.ic_logo_repsol,
        stringResource(R.string.onboarding_content_description_logo)
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewOnboarding(){
    ScreenPreview(
        uiState = OnboardingUiState(
            pages = listOf(
                OnboardingPage(
                    titleRes = R.string.onboarding_welcome,
                    descriptionRes = R.string.onboarding_welcome_description,
                    imageRes = R.drawable.ic_rocket
                )
            )
        )
    ) {
        OnboardingScreen()
    }
}


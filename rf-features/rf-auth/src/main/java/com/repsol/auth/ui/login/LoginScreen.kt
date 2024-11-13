package com.repsol.auth.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.repsol.auth.ui.login.interactor.LoginUiState
import com.repsol.components.button.RFButton
import com.repsol.components.icon.RFIcon
import com.repsol.components.style.RFColor
import com.repsol.components.style.RFTextStyle
import com.repsol.components.text.RFText
import com.repsol.core_ui.stateful.ChildStateful
import com.repsol.core_ui.stateful.ScreenPreview
import com.repsol.core_ui.stateful.Stateful
import com.repsol.navigation.MainGraph
import com.repsol.navigation.core.localNavController
import com.repsol.rf_assets.R
import com.repsol.tools.components.DisplayImage
import com.repsol.tools.components.ReusableSpacerVertical
import com.repsol.auth.ui.login.interactor.LoginUiEvent as UiEvent
import com.repsol.auth.ui.login.interactor.LoginUiIntent as UiIntent

@Composable
fun LoginScreen() = Stateful<LoginViewModel> {
    val navController = localNavController()
    OnUiEvent {
        when (it) {
            is UiEvent.GoToHome -> {
                navController.navigate(MainGraph.GestorDashboardModule)
            }
        }
    }

    LoginContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent() {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.ic_car),
                contentDescription = stringResource(R.string.image_car),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            ReusableSpacerVertical(24.dp)

            AuthBody()
            AuthFooter()
        }

        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent
            ),
            title = {
                DisplayImage(
                    modifier = Modifier.fillMaxWidth(),
                    drawableRes = R.drawable.ic_logo_repsol,
                    contentDescription = stringResource(R.string.content_description_logo)
                )
            },
            scrollBehavior = scrollBehavior,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun AuthFooter() = ChildStateful<LoginViewModel> {

    Column {
        RFButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.login),
            onClick = { execUiIntent(UiIntent.OnLoginClick) }
        )
    }
}

@Composable
fun AuthBody() = ChildStateful<LoginViewModel> {

    val uiState by uiState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RFText(
            text = stringResource(R.string.welcome_flotas), textStyle = RFTextStyle.Roboto(
                color = RFColor.UxComponentColorDarkOrange,
                fontSize = 28.sp
            )
        )
        ReusableSpacerVertical(24.dp)
        RFText(
            text = stringResource(R.string.email), textStyle = RFTextStyle.Roboto(
                fontSize = 12.sp,
                color = RFColor.UxComponentColorCharcoal
            ), modifier = Modifier.align(Alignment.Start)
        )
        ReusableSpacerVertical(4.dp)
        EmailTextField(
            uiState = uiState,
            onEmailChanged = { execUiIntent(UiIntent.OnEmailChanged(it)) },
            onClearEmail = { execUiIntent(UiIntent.OnClearEmailClick) }
        )
        ReusableSpacerVertical(24.dp)
        RFText(
            text = stringResource(R.string.password),
            textStyle = RFTextStyle.Roboto(
                fontSize = 12.sp,
                color = RFColor.UxComponentColorCharcoal
            ), modifier = Modifier.align(Alignment.Start)
        )
        ReusableSpacerVertical(4.dp)
        PasswordTextField(
            uiState = uiState,
            onPasswordChanged = { execUiIntent(UiIntent.OnPasswordChanged(it)) },
            onClearPassword = { execUiIntent(UiIntent.OnClearPasswordClick) },
            togglePassword = { execUiIntent(UiIntent.OnTogglePasswordVisibility) }
        )
        ReusableSpacerVertical(4.dp)
        ForgotPassword(Modifier.align(Alignment.End))
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    TextButton(
        onClick = {
            //accion redigirir recuperar contraseña
        },
        modifier = modifier
    ) {
        RFText(
            text = stringResource(R.string.forgotPassword),
            textStyle = RFTextStyle.Roboto(
                fontSize = 12.sp,
                color = RFColor.UxComponentColorEasternBlue
            )
        )
    }
}

@Composable
fun PasswordTextField(
    uiState: LoginUiState,
    onPasswordChanged: (String) -> Unit,
    togglePassword: () -> Unit,
    onClearPassword: () -> Unit

) {

    OutlinedTextField(
        value = uiState.password,
        onValueChange = onPasswordChanged,
        placeholder = {
            RFText(text = stringResource(R.string.placeholder_password),
                textStyle = RFTextStyle.Roboto(
                    fontSize = 16.sp,
                    color = RFColor.UxComponentColorDarkGray
                ))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (uiState.isPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon =
                if (uiState.isPasswordVisibility) R.drawable.ic_show_password else R.drawable.ic_hide_password
            Row {
                IconButton(onClick = onClearPassword) {
                    RFIcon(
                        painter = painterResource(R.drawable.ic_clear)
                    )
                }

                IconButton(onClick = togglePassword) {
                    RFIcon(
                        painter = painterResource(id = icon)
                    )
                }
            }
        },
        textStyle = TextStyle(
            textDecoration = TextDecoration.None
        ),
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun EmailTextField(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onClearEmail: () -> Unit
) {

    Column(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = uiState.email,
            onValueChange = onEmailChanged,
            placeholder = {
                RFText(text = stringResource(R.string.placeholder_email),
                    textStyle = RFTextStyle.Roboto(
                        fontSize = 16.sp,
                        color = RFColor.UxComponentColorDarkGray
                    ))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            trailingIcon = {
                IconButton(onClick = onClearEmail) {
                    RFIcon(
                        painter = painterResource(R.drawable.ic_clear)
                    )
                }
            },
            textStyle = TextStyle(
                textDecoration = TextDecoration.None
            ),
            isError = uiState.emailError != null,
            modifier = Modifier.fillMaxSize()
        )

        uiState.emailError?.let { errorText ->
            Text(
                text = errorText,
                color = Color.Red
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultLoginScreenPreview() {
    ScreenPreview(uiState = LoginUiState()) {
        LoginScreen()
    }
}
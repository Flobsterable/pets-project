package com.example.pets_project.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.model.LoginEvent
import com.example.pets_project.ui.screens.login.model.LoginSubState
import com.example.pets_project.viewModels.LoginViewModel
import com.example.pets_project.ui.screens.login.view.LoginColumn
import com.example.pets_project.ui.screens.login.view.RegistrationColumn
import com.example.pets_project.ui.screens.login.view.SwitchLoginStateRow
import com.example.pets_project.ui.theme.PetsprojectTheme
import com.example.pets_project.ui.theme.textButton

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {

    val viewState = loginViewModel.viewState.observeAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageHeader()

        SwitchLoginStateRow(
            loginSubState = viewState.value!!.loginSubState,
            clickedLoginText = { loginViewModel.obtainEvent(LoginEvent.SignInClicked) },
            clickedRegistrationText = { loginViewModel.obtainEvent(LoginEvent.SignUpClicked) }
        )

        when (viewState.value!!.loginSubState) {
            LoginSubState.Login -> LoginColumn(loginViewModel)
            LoginSubState.Registration -> RegistrationColumn(loginViewModel)
        }

        Spacer(modifier = Modifier.weight(1.0f))
        NoLoginButton(onClick = { loginViewModel.obtainEvent(LoginEvent.SignWOLoginClicked) })
    }
}

@Composable
fun ImageHeader() {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_header),
            contentDescription = stringResource(id = R.string.cd_logo_header),
            modifier = Modifier
                .padding(top = 56.dp, bottom = 44.dp, start = 104.dp, end = 104.dp)
        )
    }
}

@Composable
fun NoLoginButton(
    onClick: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp)
    ) {
        ClickableText(
            onClick = onClick,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp),
            text = AnnotatedString(stringResource(id = R.string.text_button_no_login)),
            style = textButton
        )
        Spacer(modifier = Modifier.padding(start = 8.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_button),
            contentDescription = stringResource(id = R.string.cd_arrow_no_login_button),
            modifier = Modifier
                .padding(top = 22.dp, bottom = 20.dp)
                .clickable { onClick }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsprojectTheme {
        val viewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(viewModel)
    }
}

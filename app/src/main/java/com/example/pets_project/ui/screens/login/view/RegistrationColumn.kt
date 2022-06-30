package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.model.LoginEvent
import com.example.pets_project.ui.screens.login.model.LoginViewModel

@Composable
fun RegistrationColumn(loginViewModel: LoginViewModel) {

    val viewState = loginViewModel.viewState

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextFieldColumn(
            modifier = Modifier.padding(top = 40.dp),
            value = viewState.value!!.nameValue,
            onValueChange ={loginViewModel.obtainEvent(LoginEvent.NameChanged(it))},
            placeholderIdString = R.string.edit_text_name,
            errorState = viewState.value!!.nameTextErrorState,
            keyboardType = KeyboardType.Text
        )

        TextFieldColumn(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.emailValue,
            onValueChange ={loginViewModel.obtainEvent(LoginEvent.EmailChanged(it)) },
            placeholderIdString = R.string.edit_text_email,
            errorState = viewState.value!!.emailTextErrorState,
            errorMessageRegex = stringResource(id = R.string.error_email_regex),
            keyboardType = KeyboardType.Email
        )

        TextFieldColumn(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.passwordValue,
            onValueChange ={loginViewModel.obtainEvent(LoginEvent.PassChanged(it))},
            placeholderIdString = R.string.edit_text_password,
            errorState =viewState.value!!.passTextErrorState,
            keyboardType = KeyboardType.Password
        )

        TextFieldColumn(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.passwordConfirmationValue,
            onValueChange ={loginViewModel.obtainEvent(LoginEvent.PassConfirmationChanged(it))},
            placeholderIdString = R.string.edit_text_repeat_password,
            errorState = viewState.value!!.passConfirmationTextErrorState,
            errorMessageValid = stringResource(id = R.string.error_pass_con),
            keyboardType = KeyboardType.Password
        )

        MarkButton(
            onClick = {loginViewModel.obtainEvent(LoginEvent.RegistrationButtonClicked)},
            modifier = Modifier.padding(top = 24.dp),
            stringResId = R.string.button_registration,
            painterResId = R.drawable.ic_mark
        )

    }
}
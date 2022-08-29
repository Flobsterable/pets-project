package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.model.LoginEvent
import com.example.pets_project.ui.screens.view.PasswordTextField
import com.example.pets_project.viewModels.LoginViewModel

@Composable
fun RegistrationColumn(loginViewModel: LoginViewModel) {

    val viewState = loginViewModel.viewState
    var passwordVisible by remember { mutableStateOf(false) }

    fun changeVisiblePassword(){
        passwordVisible = !passwordVisible
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SingleTextField(
            modifier = Modifier.padding(top = 40.dp),
            value = viewState.value!!.nameValue,
            onValueChange = { loginViewModel.obtainEvent(LoginEvent.NameChanged(it)) },
            placeholderIdString = R.string.edit_text_name,
            errorState = viewState.value!!.nameTextErrorState,
            keyboardType = KeyboardType.Text
        )

        SingleTextField(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.emailValue,
            onValueChange = { loginViewModel.obtainEvent(LoginEvent.EmailChanged(it)) },
            placeholderIdString = R.string.edit_text_email,
            errorState = viewState.value!!.emailTextErrorState,
            errorMessageValid = stringResource(id = R.string.error_email_valid),
            errorMessageRegex = stringResource(id = R.string.error_email_regex),
            keyboardType = KeyboardType.Email
        )

        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.passwordValue,
            onValueChange = { loginViewModel.obtainEvent(LoginEvent.PassChanged(it)) },
            onSetVisiblePassword = {changeVisiblePassword()},
            placeholderIdString = R.string.edit_text_password,
            errorState = viewState.value!!.passTextErrorState,
            passwordVisible = passwordVisible
        )
        PasswordTextField(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.passwordConfirmationValue,
            onValueChange = { loginViewModel.obtainEvent(LoginEvent.PassConfirmationChanged(it)) },
            onSetVisiblePassword = {changeVisiblePassword()},
            placeholderIdString = R.string.edit_text_repeat_password,
            errorState = viewState.value!!.passConfirmationTextErrorState,
            errorMessageValid = stringResource(id = R.string.error_pass_con),
            passwordVisible = passwordVisible
        )

        MarkButton(
            onClick = { loginViewModel.obtainEvent(LoginEvent.RegistrationButtonClicked) },
            modifier = Modifier.padding(top = 24.dp),
            stringResId = R.string.button_registration,
            painterResId = R.drawable.ic_mark,
            contentDescriptionResId = R.string.cd_registration
        )
    }
}

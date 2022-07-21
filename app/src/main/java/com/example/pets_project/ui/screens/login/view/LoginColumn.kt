package com.example.pets_project.ui.screens.login.view


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.model.LoginEvent
import com.example.pets_project.ui.screens.login.model.LoginViewModel
import com.example.pets_project.ui.theme.textButton


@Composable
fun LoginColumn(loginViewModel: LoginViewModel) {

    val viewState = loginViewModel.viewState
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextFieldColumn(
            modifier = Modifier.padding(top = 40.dp),
            value = viewState.value!!.emailValue,
            onValueChange = {loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))},
            placeholderIdString = R.string.edit_text_email,
            errorState =viewState.value!!.emailTextErrorState,
            errorMessageRegex = stringResource(id = R.string.error_email_regex),
            keyboardType = KeyboardType.Email
        )
        TextFieldColumn(
            modifier = Modifier.padding(top = 16.dp),
            value = viewState.value!!.passwordValue,
            onValueChange ={loginViewModel.obtainEvent(LoginEvent.PassChanged(it))},
            placeholderIdString = R.string.edit_text_password,
            errorState = viewState.value!!.passTextErrorState,
            errorMessageValid = stringResource(id = R.string.error_pass_valid),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        MarkButton(
            onClick = {loginViewModel.obtainEvent(LoginEvent.LoginButtonClicked)},
            modifier = Modifier.padding(top = 24.dp),
            stringResId = R.string.button_login ,
            painterResId = R.drawable.ic_mark )

        ClickableText(
            onClick = {loginViewModel.obtainEvent(LoginEvent.ForgotButtonClicked)},
            modifier = Modifier.padding(top = 50.5f.dp, start = 122.dp, end = 122.dp),
            text = AnnotatedString(stringResource(id = R.string.text_button_forgot)),
            style = textButton
        )
    }
}
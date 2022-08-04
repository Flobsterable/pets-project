package com.example.pets_project.ui.screens.login.model

sealed class LoginEvent {
    object SignInClicked : LoginEvent()
    object SignUpClicked : LoginEvent()
    object LoginButtonClicked : LoginEvent()
    object RegistrationButtonClicked : LoginEvent()
    object ForgotButtonClicked : LoginEvent()
    object SignWOLoginClicked : LoginEvent()

    data class EmailChanged(val value: String) : LoginEvent()
    data class PassChanged(val value: String) : LoginEvent()
    data class PassConfirmationChanged(val value: String) : LoginEvent()
    data class NameChanged(val value: String) : LoginEvent()
}

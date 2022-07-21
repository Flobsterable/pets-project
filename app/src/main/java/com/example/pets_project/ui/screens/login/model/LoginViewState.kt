package com.example.pets_project.ui.screens.login.model


enum class LoginSubState{
    Login, Registration
}

enum class EditTextErrorState{
    IsEmpty, IsNotValid, None, IsNotRegex
}

enum class AuthorizationResponse{
    Ok, WrongAccount, ExistingAccount
}

data class LoginViewState (
    val loginSubState: LoginSubState = LoginSubState.Login,
    val emailValue : String = "",
    val passwordValue : String = "",
    val passwordConfirmationValue: String = "",
    val nameValue: String = "",

    val nameTextErrorState: EditTextErrorState = EditTextErrorState.None,
    val emailTextErrorState: EditTextErrorState = EditTextErrorState.None,
    val passTextErrorState: EditTextErrorState = EditTextErrorState.None,
    val passConfirmationTextErrorState : EditTextErrorState = EditTextErrorState.None,
    )


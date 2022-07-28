package com.example.pets_project.navigation.model

sealed class AppScreens(
    val nameScreen: String
) {
    object LoginScreen : AppScreens(loginScreenName)
    object MainScreen : AppScreens(mainScreenName)
}

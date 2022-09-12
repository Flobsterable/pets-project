package com.example.pets_project.navigation.model

const val DETAIL_ARGUMENT_KEY = "data"

sealed class AppScreens(
    val nameScreen: String
) {
    object LoginScreen : AppScreens(loginScreenName)
    object MainScreen : AppScreens(mainScreenName)
    object AdScreen : AppScreens(adScreenName)
}

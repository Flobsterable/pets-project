package com.example.pets_project.navigation

import androidx.navigation.NavHostController
import com.example.pets_project.navigation.model.AppScreens

class AppNavigationImpl : AppNavigation {
    override var navHostController: NavHostController? = null

    override fun navigateTo(appScreen: AppScreens) {
        navHostController?.navigate(appScreen.nameScreen)
    }
}

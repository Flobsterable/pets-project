package com.example.pets_project.navigation

import androidx.navigation.NavHostController
import com.example.pets_project.navigation.model.AppScreens

interface AppNavigation {
    var navHostController: NavHostController?

    fun navigateTo(appScreen: AppScreens)
}

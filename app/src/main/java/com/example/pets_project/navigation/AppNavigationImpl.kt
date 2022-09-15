package com.example.pets_project.navigation

import androidx.navigation.NavHostController
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.DETAIL_ARGUMENT_KEY

class AppNavigationImpl : AppNavigation {
    override var navHostController: NavHostController? = null

    override fun navigateTo(appScreen: AppScreens) {
        navHostController?.navigate(appScreen.nameScreen)
    }
    override fun navigateTo(route: String) {
        navHostController?.navigate(route = route)
    }
    override fun navigateToWithArg(appScreen: AppScreens, key: String, value: Any?) {
        navHostController!!.currentBackStackEntry!!.savedStateHandle.set(
            key = key,
            value = value
        )
        navHostController!!.navigate(route = appScreen.nameScreen)
    }
}

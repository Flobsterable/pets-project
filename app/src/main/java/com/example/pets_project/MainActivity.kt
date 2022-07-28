package com.example.pets_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.loginNavGraph
import com.example.pets_project.navigation.model.mainNavGraph
import com.example.pets_project.repository.Repository
import com.example.pets_project.ui.screens.login.LoginScreen
import com.example.pets_project.ui.screens.login.model.LoginViewModel
import com.example.pets_project.ui.theme.PetsprojectTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var navigation: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startLocation = when (repository.isHasAuthToken()) {
            false -> loginNavGraph
            true -> mainNavGraph
        }

        setContent {
            val navController = rememberNavController()
            navigation.navHostController = navController

            PetsprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {

                    NavHost(navController = navController, startDestination = startLocation) {

                        navigation(
                            startDestination = AppScreens.LoginScreen.nameScreen,
                            route = loginNavGraph
                        ) {
                            composable(route = AppScreens.LoginScreen.nameScreen) {
                                val viewModel = hiltViewModel<LoginViewModel>()
                                LoginScreen(loginViewModel = viewModel)
                            }
                        }
                        navigation(
                            startDestination = AppScreens.MainScreen.nameScreen,
                            route = mainNavGraph
                        ) {
                            composable(route = AppScreens.MainScreen.nameScreen) {
                                Log.e("navigation", "go to main screen")
                            }
                        }
                    }
                }
            }
        }
    }
}

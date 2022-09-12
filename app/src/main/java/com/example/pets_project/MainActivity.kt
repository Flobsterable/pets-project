package com.example.pets_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.DETAIL_ARGUMENT_KEY
import com.example.pets_project.navigation.model.loginNavGraph
import com.example.pets_project.navigation.model.mainNavGraph
import com.example.pets_project.repository.Repository
import com.example.pets_project.ui.screens.login.LoginScreen
import com.example.pets_project.ui.screens.main.MainScreen
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.adsList.AdScreen
import com.example.pets_project.ui.theme.PetsprojectTheme
import com.example.pets_project.viewModels.AdViewModel
import com.example.pets_project.viewModels.LoginViewModel
import com.example.pets_project.viewModels.MainViewModel
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
                                val viewModel = hiltViewModel<MainViewModel>()
                                MainScreen(viewModel)
                            }
                            composable(
                                route = AppScreens.AdScreen.nameScreen
                            ) {
                                val viewModel = hiltViewModel<AdViewModel>()
                                Log.e("","${navController.currentBackStackEntry?.destination}")
                                navController.previousBackStackEntry!!.savedStateHandle
                                    .get<AdViewData>(DETAIL_ARGUMENT_KEY).let {
                                        AdScreen(viewModel, it)
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.pets_project.ui.screens.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.BottomBarItem
import com.example.pets_project.ui.screens.main.addAd.AddAdScreen
import com.example.pets_project.ui.screens.main.adsList.AdScreen
import com.example.pets_project.ui.screens.main.adsList.AdsListScreen
import com.example.pets_project.ui.screens.main.view.MainScreenBottomBar
import com.example.pets_project.viewModels.AddAdViewModel
import com.example.pets_project.viewModels.AdsListViewModel
import com.example.pets_project.viewModels.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val bottomNavGraph = rememberNavController()
    val navBackStackEntry by bottomNavGraph.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            MainScreenBottomBar(
                currentRoute = currentDestination?.route,
                onItemClick = {
                    bottomNavGraph.navigate(it) {
                        bottomNavGraph.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }

    ) {
        NavHost(
            navController = bottomNavGraph,
            startDestination = BottomBarItem.ListTab.route
        ) {
            composable(route = BottomBarItem.ListTab.route) {
                val viewModel = hiltViewModel<AdsListViewModel>()
                AdsListScreen(adsListViewModel = viewModel)
            }
            composable(route = BottomBarItem.AddTab.route) {
                when (mainViewModel.isHasAuthToken()) {
                    true -> {
                        val viewModel = hiltViewModel<AddAdViewModel>()
                        AddAdScreen(addAdViewModel = viewModel)
                    }
                    false -> {
                        mainViewModel.navigateTo(AppScreens.LoginScreen)
                    }
                }
            }
            composable(route = BottomBarItem.ProfileTab.route) {
            }
        }
    }
}

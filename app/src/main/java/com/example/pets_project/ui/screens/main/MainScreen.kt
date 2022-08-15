package com.example.pets_project.ui.screens.main

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pets_project.navigation.model.BottomBarItem
import com.example.pets_project.repository.Repository
import com.example.pets_project.ui.screens.login.LoginScreen
import com.example.pets_project.ui.screens.main.addAd.AddAdScreen
import com.example.pets_project.ui.screens.main.view.MainScreenBottomBar
import com.example.pets_project.viewModels.AddAdViewModel
import com.example.pets_project.viewModels.LoginViewModel
import com.example.pets_project.viewModels.MainViewModel
import javax.inject.Inject

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val bottomNavGraph = rememberNavController()
    val currentTab = remember {
        MutableLiveData(BottomBarItem.ListTab.route)
    }
    val tabState = currentTab.observeAsState(BottomBarItem.ListTab.route)

    Scaffold(
        bottomBar = {
            MainScreenBottomBar(
                currentRoute = tabState.value,
                onItemClick = {
                    bottomNavGraph.navigate(it) {
                        bottomNavGraph.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                            currentTab.postValue(it)
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
                Log.e("bottom nav", "list")
            }
            composable(route = BottomBarItem.AddTab.route) {
                when (mainViewModel.isHasAuthToken()) {
                    true -> {
                        val viewModel = hiltViewModel<AddAdViewModel>()
                        AddAdScreen(addAdViewModel = viewModel)
                    }
                    else -> {
                        val viewModel = hiltViewModel<LoginViewModel>()
                        LoginScreen(loginViewModel = viewModel)
                    }
                }
            }
            composable(route = BottomBarItem.ProfileTab.route) {
                Log.e("bottom nav", "profile")
            }
        }
    }
}

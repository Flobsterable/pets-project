package com.example.pets_project.viewModels

import androidx.lifecycle.ViewModel
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val navigation: AppNavigation
) : ViewModel() {
    fun isHasAuthToken(): Boolean {
        return repository.isHasAuthToken()
    }

    fun navigateTo(appScreens: AppScreens) {
        navigation.navigateTo(appScreen = appScreens)
    }
}

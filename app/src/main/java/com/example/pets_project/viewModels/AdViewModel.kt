package com.example.pets_project.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.ui.screens.ad.model.AdEvent
import com.example.pets_project.ui.screens.ad.model.AdState
import com.example.pets_project.ui.screens.ad.model.AdSubState
import com.example.pets_project.ui.screens.ad.model.AdViewState
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdViewModel @Inject constructor(
    private val navigation: AppNavigation
) : ViewModel(), EventHandler<AdEvent> {

    private val _viewState = MutableLiveData(AdViewState())
    val viewState: LiveData<AdViewState> = _viewState

    override fun obtainEvent(event: AdEvent) {
        when (event) {
            is AdEvent.ChangeState -> changeState(event.value)
            is AdEvent.SaveData -> saveData(event.value)
        }
    }

    private fun saveData(data: AdViewData) {
        _viewState.postValue(_viewState.value!!.copy(adData = data, adState = AdState.Complete))
    }

    private fun changeState(adSubState: AdSubState) {
        _viewState.postValue(_viewState.value?.copy(adSubState = adSubState))
    }

    fun popBackStack() {
        navigation.navHostController?.popBackStack()
    }
}

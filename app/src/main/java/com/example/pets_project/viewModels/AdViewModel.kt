package com.example.pets_project.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.ui.screens.ad.model.AdEvent
import com.example.pets_project.ui.screens.ad.model.AdState
import com.example.pets_project.ui.screens.ad.model.AdSubState
import com.example.pets_project.ui.screens.ad.model.AdViewState
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val network: NetworkService
) : ViewModel(), EventHandler<AdEvent> {

    private val _viewState = MutableLiveData(AdViewState())
    val viewState: LiveData<AdViewState> = _viewState

    override fun obtainEvent(event: AdEvent) {
        when (event) {
            is AdEvent.ChangeState -> changeState(event.value)
            is AdEvent.GetViewData -> getViewData(event.value)
        }
    }

    private fun getViewData(id: Int) {
        viewModelScope.launch {
            val adViewData = network.getAd(id)
            when (adViewData != null) {
                true -> {
                    Log.e("ad viewModel", "$adViewData")
                    _viewState.postValue(_viewState.value!!.copy(adData = adViewData, adState = AdState.Complete))
                }
                false -> {
                    _viewState.postValue((_viewState.value!!.copy(adState = AdState.Empty)))
                }
            }
        }
    }

    private fun changeState(adSubState: AdSubState) {
        _viewState.postValue(_viewState.value?.copy(adSubState = adSubState))
    }

    fun popBackStack() {
        navigation.navHostController?.popBackStack()
    }
}

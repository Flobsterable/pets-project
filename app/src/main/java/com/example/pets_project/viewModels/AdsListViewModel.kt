package com.example.pets_project.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.DETAIL_ARGUMENT_KEY
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.adsList.model.AdsListEvent
import com.example.pets_project.ui.screens.main.adsList.model.AdsListViewState
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsListViewModel @Inject constructor(
    private val network: NetworkService,
    private val navigation: AppNavigation,
) : ViewModel(), EventHandler<AdsListEvent> {

    private val _viewState = MutableLiveData(AdsListViewState())
    val viewState: LiveData<AdsListViewState> = _viewState

    override fun obtainEvent(event: AdsListEvent) {
        when (event) {

            is AdsListEvent.OpenAd -> openAd(event.value)
            is AdsListEvent.ChangeFilterOption -> changeFilterOption(event.value)

            AdsListEvent.GetAdList -> getAdList()
        }
    }

    private fun openAd(value: AdViewData) {
        navigation.navigateToWithArg(AppScreens.AdScreen, DETAIL_ARGUMENT_KEY, value)
        Log.e("open ad", "${navigation.navHostController!!.graph}")
    }

    private fun changeFilterOption(value: PetType) {
        _viewState.postValue(_viewState.value?.copy(petsTypeFilter = value))
    }

    private fun getAdList() {
        viewModelScope.launch {
            val adList = network.getAdList(viewState.value!!.petsTypeFilter)
            if (adList != null) {
                _viewState.postValue(_viewState.value?.copy(adsList = adList))
            }
        }
    }
}

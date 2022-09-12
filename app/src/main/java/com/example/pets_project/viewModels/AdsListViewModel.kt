package com.example.pets_project.viewModels

import android.content.Context
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.navigation.model.AppScreens
import com.example.pets_project.navigation.model.DETAIL_ARGUMENT_KEY
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.models.AdData
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

    lateinit var currentContext: Context

    override fun obtainEvent(event: AdsListEvent) {
        when (event) {

            is AdsListEvent.OpenAd -> openAd(event.value)
            is AdsListEvent.ChangeFilterOption -> changeFilterOption(event.value)
            is AdsListEvent.GetAdList -> getAdsList(event.value)
        }
    }

    private fun openAd(value: AdViewData) {
        navigation.navHostController!!.currentBackStackEntry!!.savedStateHandle.set(
            key = DETAIL_ARGUMENT_KEY,
            value = value
        )
        navigation.navHostController!!.navigate(AppScreens.AdScreen.nameScreen) {
        }
        Log.e("open ad", "${navigation.navHostController!!.graph}")
    }

    private fun changeFilterOption(value: PetType) {
        _viewState.postValue(_viewState.value?.copy(petsTypeFilter = value))
    }

    private fun getAdsList(context: Context) {
        currentContext = context
        viewModelScope.launch {
            val type = when (viewState.value!!.petsTypeFilter) {
                PetType.Dog -> "dog"
                PetType.Cat -> "cat"
                PetType.Other -> "other"
                else -> ""
            }
            val adList = network.getAds(type)
            if (adList != null) {
                val viewList = adListParser(adList)
                _viewState.postValue(_viewState.value?.copy(adsList = viewList))
            }
        }
    }
    private fun adListParser(adList: List<AdData>): List<AdViewData> {
        val adViewList = mutableListOf<AdViewData>()
        for (item in adList) {
            adViewList.add(adParser(item))
        }
        return adViewList
    }

    private fun adParser(adData: AdData): AdViewData {

        val geocoder = Geocoder(currentContext)
        val fullAddress = geocoder.getFromLocation(
            adData.geoPosition.lat,
            adData.geoPosition.lng,
            1
        )

        val address = when (fullAddress.isNotEmpty()) {
            true -> fullAddress[0].getAddressLine(0)
            false -> ""
        }

        return AdViewData(
            id = adData.id,
            nameAd = adData.title,
            descriptionAd = adData.description,
            petType = when (adData.petType) {
                "cat" -> PetType.Cat
                "dog" -> PetType.Dog
                else -> PetType.Other
            },
            photoUri = Uri.parse(adData.imageUrl),
            location = arrayOf(adData.geoPosition.lat, adData.geoPosition.lng),
            address = address
        )
    }
}

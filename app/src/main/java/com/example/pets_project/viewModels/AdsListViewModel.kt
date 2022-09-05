package com.example.pets_project.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.ui.screens.main.adsList.model.AdsListEvent
import com.example.pets_project.ui.screens.main.adsList.model.AdsListViewState
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsListViewModel @Inject constructor(
    private val network: NetworkService
) : ViewModel(), EventHandler<AdsListEvent> {

    private val _viewState = MutableLiveData(AdsListViewState())
    val viewState: LiveData<AdsListViewState> = _viewState

    override fun obtainEvent(event: AdsListEvent) {
        when (event) {

            is AdsListEvent.OpenAd -> TODO()
            is AdsListEvent.ChangeFilterOption -> changeFilterOption(event.value)
        }
    }

    private fun changeFilterOption(value: PetType) {
        _viewState.postValue(_viewState.value?.copy(petsTypeFilter = value))
    }

    fun getAdsList() {
        viewModelScope.launch {
            val type = when (viewState.value!!.petsTypeFilter) {
                PetType.Dog -> "dog"
                PetType.Cat -> "cat"
                PetType.Other -> "other"
                else -> ""
            }
            val adList = network.getAds(type)
            if (adList != null)
                _viewState.postValue(_viewState.value?.copy(adsList = adList))
        }
    }
}

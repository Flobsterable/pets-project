package com.example.pets_project.viewModels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.navigation.AppNavigation
import com.example.pets_project.repository.Repository
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.services.network.models.GeoPosition
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.ui.screens.main.addAd.model.AddAdSubState
import com.example.pets_project.ui.screens.main.addAd.model.AddAdViewState
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.utils.EditTextErrorState
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAdViewModel @Inject constructor(
    private val networkService: NetworkService,
    private val repository: Repository,
    private val navigation: AppNavigation
) : ViewModel(), EventHandler<AddAdEvent> {

    private val _viewState = MutableLiveData(AddAdViewState())
    val viewState: LiveData<AddAdViewState> = _viewState

    override fun obtainEvent(event: AddAdEvent) {
        when (event) {
            AddAdEvent.AddAddress -> addAddress()
            AddAdEvent.PlaceAd -> placeAd()
            AddAdEvent.ConfirmAddress -> confirmAddress()
            AddAdEvent.GetCurrentLocation -> TODO()

            is AddAdEvent.DescriptionAdChanged -> descriptionAdChanged(event.value)
            is AddAdEvent.NameAdChanged -> nameAdChanged(event.value)
            is AddAdEvent.TypePetChanged -> typePetChanged(event.value)
            is AddAdEvent.PhotoChanged -> photoChanged(event.value)
            is AddAdEvent.ChangedState -> changeState(event.value)
        }
    }

    private fun nameAdChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(adName = value))
    }

    private fun descriptionAdChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(adDescription = value))
    }

    private fun typePetChanged(value: PetType) {
        _viewState.postValue(_viewState.value?.copy(petType = value))
    }

    private fun photoChanged(value: Uri) {

        var vl = _viewState.value

        vl = vl?.copy(photo = value)
        vl = vl?.copy(addAdSubState = AddAdSubState.PhotoPreview)

        _viewState.postValue(vl)
    }

    private fun confirmAddress() {
        changeState(AddAdSubState.AdDescription)
    }

    private fun refreshErrorMessages(): AddAdViewState {

        return _viewState.value?.copy(
            adNameTextErrorState = EditTextErrorState.None,
            adDescriptionTextErrorState = EditTextErrorState.None
        )!!
    }

    private fun placeAd() {

        var vl = refreshErrorMessages()
        var isFieldError = false

        with(viewState) {
            if (value?.adName == "") {
                vl = vl.copy(adNameTextErrorState = EditTextErrorState.IsEmpty)
                isFieldError = true
            }

            if (value?.adDescription == "") {
                vl = vl.copy(adDescriptionTextErrorState = EditTextErrorState.IsEmpty)
                isFieldError = true
            }
        }
        _viewState.postValue(vl)

        if (!isFieldError) {
            pushAdToServer()
        }
    }

    private fun pushAdToServer() {
        viewModelScope.launch {
            val isPostSuccess = networkService.postAd(
                adData = AdData(
                    petType = viewState.value?.petType.toString(),
                    imageUrl = viewState.value?.photo.toString(),
                    title = viewState.value!!.adName,
                    description = viewState.value!!.adDescription,
                    geoPosition = GeoPosition(0.0, 0.0)
                )
            )
            when (isPostSuccess) {
                true -> Log.e("post", "success")
                false -> Log.e("post", "false")
            }
        }
    }

    private fun addAddress() {
        changeState(AddAdSubState.AddAddress)
    }

    private fun changeState(addAdSubState: AddAdSubState) {

        var vl = _viewState.value

        vl = vl?.copy(addAdSubState = addAdSubState)
        Log.e("change state", "$addAdSubState")
        _viewState.postValue(vl)
    }
}

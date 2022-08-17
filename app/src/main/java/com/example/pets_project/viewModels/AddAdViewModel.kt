package com.example.pets_project.viewModels

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pets_project.repository.Repository
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.ui.screens.main.addAd.model.AddAdSubState
import com.example.pets_project.ui.screens.main.addAd.model.AddAdViewState
import com.example.pets_project.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddAdViewModel @Inject constructor(
    private val networkService: NetworkService,
    private val repository: Repository
) : ViewModel(), EventHandler<AddAdEvent> {

    private val _viewState = MutableLiveData(AddAdViewState())
    val viewState: LiveData<AddAdViewState> = _viewState

    override fun obtainEvent(event: AddAdEvent) {
        when (event) {
            AddAdEvent.AddAddress -> addAddress()
            //  AddAdEvent.AddPhoto -> addPhoto(event.value)
            AddAdEvent.PlaceAd -> placeAd()
            AddAdEvent.ConfirmAddress -> confirmAddress()
            AddAdEvent.GetCurrentLocation -> TODO()

            is AddAdEvent.DescriptionAdChanged -> TODO()
            is AddAdEvent.NameAdChanged -> TODO()
            is AddAdEvent.TypePetChanged -> TODO()
            is AddAdEvent.PhotoChanged -> photoChanged(event.value)
            is AddAdEvent.ChangedState -> changeState(event.value)
        }
    }

    private fun confirmAddress() {
        changeState(AddAdSubState.AdDescription)
    }

    private fun placeAd() {
        TODO("Not yet implemented")
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

    private fun photoChanged(value: Uri) {

        var vl = _viewState.value

        vl = vl?.copy(photo = value)
        vl = vl?.copy(addAdSubState = AddAdSubState.PhotoPreview)

        _viewState.postValue(vl)
    }
}

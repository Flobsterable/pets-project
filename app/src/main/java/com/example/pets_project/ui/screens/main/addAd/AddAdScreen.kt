package com.example.pets_project.ui.screens.main.addAd

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.ui.screens.main.addAd.model.AddAdSubState
import com.example.pets_project.ui.screens.main.addAd.model.AddAdViewState
import com.example.pets_project.ui.screens.main.addAd.view.AdDescriptionColumn
import com.example.pets_project.ui.screens.main.addAd.view.AddAddressColumn
import com.example.pets_project.ui.screens.main.addAd.view.AddPhotoColumn
import com.example.pets_project.ui.screens.main.addAd.view.PhotoReviewColumn
import com.example.pets_project.ui.theme.topBarTitle
import com.example.pets_project.utils.AddAdChangeCallback
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AddAdScreen(addAdViewModel: AddAdViewModel) {
    val viewState = addAdViewModel.viewState.observeAsState()

    Scaffold(
        topBar = {
            AddAdTopBar(viewState = viewState)
            {  addAdViewModel.obtainEvent(AddAdEvent.ChangedState(it))}

        }
    ) {
        when (viewState.value!!.addAdSubState) {
            AddAdSubState.AddPhoto -> AddPhotoColumn(addAdViewModel = addAdViewModel)
            AddAdSubState.PhotoPreview -> PhotoReviewColumn(addAdViewModel = addAdViewModel)
            AddAdSubState.AddAddress -> AddAddressColumn(addAdViewModel = addAdViewModel)
            AddAdSubState.AdDescription -> AdDescriptionColumn(addAdViewModel = addAdViewModel)
        }
    }
}
@Composable
fun AddAdTopBarTitle(viewState: State<AddAdViewState?>) {
    Text(
        text = when (viewState.value!!.addAdSubState) {
            AddAdSubState.AddPhoto -> stringResource(id = R.string.tb_load_photo)
            AddAdSubState.PhotoPreview -> stringResource(id = R.string.tb_load_photo)
            AddAdSubState.AddAddress -> stringResource(id = R.string.tb_add_location)
            AddAdSubState.AdDescription -> stringResource(id = R.string.tb_add_description)
        },
        style = topBarTitle,
        textAlign = TextAlign.Center
    )
}

@Composable
fun AddAdTopBar(viewState: State<AddAdViewState?>, onClick : AddAdChangeCallback) {

    TopAppBar(
        modifier = Modifier.height(64.dp),
        title = { AddAdTopBarTitle(viewState = viewState) },
        navigationIcon = {
            IconButton(onClick = {
                when (viewState.value!!.addAdSubState) {
                    AddAdSubState.AddPhoto -> onClick(null)
                    AddAdSubState.PhotoPreview -> onClick(AddAdSubState.AddPhoto)
                    AddAdSubState.AddAddress -> onClick(AddAdSubState.PhotoPreview)
                    AddAdSubState.AdDescription -> onClick(AddAdSubState.AddAddress)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_white_arrow_button),
                    contentDescription = stringResource(id = R.string.cd_add_ad_photo),
                    modifier = Modifier
                        .scale(-1f, 1f)
                        .size(32.dp)
                )
            }
        }
    )
}

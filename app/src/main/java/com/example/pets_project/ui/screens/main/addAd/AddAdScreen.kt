package com.example.pets_project.ui.screens.main.addAd

import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.addAd.model.AddAdSubState
import com.example.pets_project.ui.screens.main.addAd.model.AddAdViewState
import com.example.pets_project.ui.screens.main.addAd.view.AdDescriptionColumn
import com.example.pets_project.ui.screens.main.addAd.view.AddAddressColumn
import com.example.pets_project.ui.screens.main.addAd.view.AddPhotoColumn
import com.example.pets_project.ui.screens.main.addAd.view.PhotoReviewColumn
import com.example.pets_project.ui.theme.topBarTitle
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AddAdScreen(addAdViewModel: AddAdViewModel) {
    val viewState = addAdViewModel.viewState.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(

            ) { AddAdTopBarTitle(viewState = viewState) }
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
            AddAdSubState.AddAddress -> stringResource(id = R.string.tb_load_photo)
            AddAdSubState.AdDescription -> stringResource(id = R.string.tb_load_photo)
        },
        style = topBarTitle
    )
}

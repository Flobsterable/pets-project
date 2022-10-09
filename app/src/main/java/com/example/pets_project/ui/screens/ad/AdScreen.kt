package com.example.pets_project.ui.screens.main.adsList

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.ad.MapView
import com.example.pets_project.ui.screens.ad.model.AdEvent
import com.example.pets_project.ui.screens.ad.model.AdState
import com.example.pets_project.ui.screens.ad.model.AdSubState
import com.example.pets_project.ui.screens.ad.view.AdEmptyColumn
import com.example.pets_project.ui.screens.ad.view.AdScreenColumn
import com.example.pets_project.ui.screens.ad.view.ImageView
import com.example.pets_project.ui.theme.topBarTitle
import com.example.pets_project.viewModels.AdViewModel

@Composable
fun AdScreen(adViewModel: AdViewModel, id: Int) {

    val viewState = adViewModel.viewState.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        adViewModel.obtainEvent(AdEvent.GetViewData(id))
    })

    when (viewState.value?.adState) {
        AdState.Loading -> {}
        AdState.Complete -> Scaffold(
            topBar = {
                AdTopBar(viewState.value!!.adData!!.nameAd!!, adViewModel)
            }
        ) {
            when (viewState.value!!.adSubState) {
                AdSubState.Ad -> AdScreenColumn(viewState = viewState) {
                    adViewModel.obtainEvent(AdEvent.ChangeState(it))
                }
                AdSubState.Map -> MapView(
                    geoPosition = viewState.value!!.adData!!.location
                )
                AdSubState.Image -> ImageView(imageUri = viewState.value!!.adData!!.photoUri.toString())
            }
        }
        AdState.Empty -> {
            AdEmptyColumn {
                adViewModel.popBackStack()
            }
        }
        null -> TODO()
    }
}

@Composable
fun AdTopBar(value: String, adViewModel: AdViewModel,) {

    TopAppBar(
        modifier = Modifier.height(64.dp),
        title = { Text(text = value, style = topBarTitle, maxLines = 2) },
        navigationIcon = {
            IconButton(onClick = {
                when (adViewModel.viewState.value!!.adSubState == AdSubState.Ad) {
                    true -> { adViewModel.popBackStack() }
                    false -> { adViewModel.obtainEvent(AdEvent.ChangeState(AdSubState.Ad)) }
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_white_arrow_button),
                    contentDescription = stringResource(id = R.string.cd_button_back),
                    modifier = Modifier
                        .scale(-1f, 1f)
                        .size(32.dp)
                )
            }
        }
    )
}

package com.example.pets_project.ui.screens.main.adsList

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.adsList.model.AdsListEvent
import com.example.pets_project.ui.screens.main.adsList.view.AdListFilterRow
import com.example.pets_project.ui.screens.main.adsList.view.AdsListItemView
import com.example.pets_project.ui.theme.editTextBackground
import com.example.pets_project.viewModels.AdsListViewModel
import kotlinx.coroutines.launch

@Composable
fun AdsListScreen(adsListViewModel: AdsListViewModel) {

    val viewState = adsListViewModel.viewState.observeAsState()
    val navController = rememberNavController()

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        adsListViewModel.obtainEvent(AdsListEvent.GetAdList(context))
    })

    Column(
        modifier = Modifier
            .padding(bottom = 56.dp)
            .fillMaxWidth(),
        // horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AdListFilterRow(
            petState = viewState.value!!.petsTypeFilter,
            onClick = { adsListViewModel.obtainEvent(AdsListEvent.ChangeFilterOption(it)) }
        )
        when (viewState.value?.adsList?.isNotEmpty()) {
            true -> AdsList(
                adsList = viewState.value?.adsList!!) {
                adsListViewModel.obtainEvent(AdsListEvent.OpenAd(it))
            }
            false -> NoAdsText()
            null -> NoAdsText()
        }
    }
}

@Composable
fun AdsList(adsList: List<AdViewData>, onClick: (AdViewData) -> Unit) {

    val state = rememberLazyGridState()
    val coroutinesScope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            state.firstVisibleItemIndex > 0
        }
    }
    Log.e("list","$state")

    @Composable
    fun ScrollToTopButton(){
        Button(onClick = {
            coroutinesScope.launch {
            state.animateScrollToItem(index = 0)
        } }) {}
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        content = {
            items(adsList) {
                    item ->
                AdsListItemView(adViewData = item, onClick = onClick)
            }
        }
    )


    AnimatedVisibility(visible = showButton) {
        Log.e("list","show button")
        ScrollToTopButton()
    }


}
@Composable
fun NoAdsText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.text_no_ads),
            style = editTextBackground
        )
    }
}

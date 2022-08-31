package com.example.pets_project.ui.screens.main.adsList

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pets_project.R
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.ui.screens.main.adsList.model.AdsListEvent
import com.example.pets_project.ui.screens.main.adsList.view.AdListFilterRow
import com.example.pets_project.ui.screens.main.adsList.view.AdsListItemView
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.ui.theme.editTextBackground
import com.example.pets_project.viewModels.AdsListViewModel

@Composable
fun AdsListScreen(adsListViewModel: AdsListViewModel) {

    val viewState = adsListViewModel.viewState.observeAsState()

    adsListViewModel.getAdsList()

    Column(
        modifier = Modifier.fillMaxSize(),
       // horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AdListFilterRow(
            petState = viewState.value!!.petsTypeFilter,
            onClick = {adsListViewModel.obtainEvent(AdsListEvent.ChangeFilterOption(it))}
        )
        when(viewState.value?.adsList?.isNotEmpty()){
            true -> AdsList(adsList = viewState.value?.adsList!!)
            false -> NoAdsText()
            null -> NoAdsText()
        }
    }
}

@Composable
fun AdsList(adsList: List<AdData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp).fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        content = {
            items(adsList) {
                    item ->
                AdsListItemView(adData = item) }
        }

    )
}
@Composable
fun NoAdsText(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.text_no_ads),
            style = editTextBackground)
    }
}

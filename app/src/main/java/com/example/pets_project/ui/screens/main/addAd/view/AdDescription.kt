package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AdDescriptionColumn(addAdViewModel: AddAdViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        MarkButton(
            onClick = { addAdViewModel.obtainEvent(AddAdEvent.PlaceAd) },
            modifier = Modifier,
            stringResId = R.string.button_place_ad,
            painterResId = R.drawable.ic_done,
            contentDescriptionResId = R.string.cd_load_image
        )
    }
}

package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.utils.Callback
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun PhotoReviewColumn(addAdViewModel: AddAdViewModel) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.aspectRatio(1f)
        ) {
            AsyncImage(
                model = addAdViewModel.viewState.value?.photo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                contentDescription = stringResource(id = R.string.cd_add_ad_photo)
            )
        }
        ArrowButton(
            onClick = { addAdViewModel.obtainEvent(AddAdEvent.AddAddress) },
            modifier = Modifier.padding(top = 158.dp),
        )
    }
}

@Composable
fun ArrowButton(
    onClick: Callback,
    modifier: Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 32.dp),
            text = stringResource(id = R.string.button_add_address),
            style = MaterialTheme.typography.button
        )
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Image(
            modifier = Modifier.padding(end = 32.dp),
            painter = painterResource(id = R.drawable.ic_white_arrow_button),
            contentDescription = stringResource(R.string.cd_arrow_no_login_button),
        )
    }
}

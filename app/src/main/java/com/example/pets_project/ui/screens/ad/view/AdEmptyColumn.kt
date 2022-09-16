package com.example.pets_project.ui.screens.ad.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton

@Composable
fun AdEmptyColumn(backCallback: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MarkButton(
            onClick = backCallback,
            modifier = Modifier.padding(start = 82.dp, end = 82.dp).height(56.dp),
            stringResId = R.string.button_back_to_ads,
            painterResId = R.drawable.ic_white_arrow_button,
            contentDescriptionResId = R.string.button_back_to_ads
        )
    }
}

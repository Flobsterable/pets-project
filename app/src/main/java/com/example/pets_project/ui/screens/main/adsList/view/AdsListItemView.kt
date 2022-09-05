package com.example.pets_project.ui.screens.main.adsList.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pets_project.R
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.ui.theme.Typography
import com.example.pets_project.ui.theme.adHeaderText
import com.example.pets_project.ui.theme.subText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdsListItemView(adData: AdData) {

    Card(
        modifier = Modifier
            .shadow(8.dp, shape = RoundedCornerShape(8.dp))
            .size(168.dp, 283.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = {}
    ) {
        Column() {

            AsyncImage(
                model = when (adData.imageUrl != "") {
                    true -> { adData.imageUrl }
                    false -> painterResource(id = R.drawable.ic_empty_image)
                },
                contentDescription = stringResource(id = R.string.cd_pet_photo),
                modifier = Modifier.size(177.dp, 168.dp).background(Color.Black),
                contentScale = ContentScale.FillWidth

            )
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp)) {
                Text(
                    text = adData.title,
                    style = adHeaderText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = adData.description,
                    style = Typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth().padding(top = 4.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location_point),
                        contentDescription = stringResource(id = R.string.cd_location_point),
                        modifier = Modifier.size(24.dp), alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "ул. Партизана Железняка, 34/2",
                        style = subText
                    )
                }
            }
        }
    }
}

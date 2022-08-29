package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pets_project.ui.screens.main.addAd.model.PetsTypeButtonItem
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.ui.theme.*

@Composable
fun PetsTypeButton(
    onClick: (PetType) -> Unit,
    petsTypeButton: PetsTypeButtonItem,
    petTypeState: PetType
) {
    Button(
        onClick = { onClick(petsTypeButton.type) },
        modifier = Modifier
            .size(96.dp)
            .shadow(6.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp),
        colors = when (petsTypeButton.type == petTypeState) {
            true -> ButtonDefaults.buttonColors(backgroundColor = accept)
            false -> ButtonDefaults.buttonColors(backgroundColor = Color.White)
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(petsTypeButton.painterResId),
                contentDescription = stringResource(petsTypeButton.contentDescriptionResId),
                tint = when (petsTypeButton.type == petTypeState) {
                    true -> Color.White
                    false -> primary
                },
                modifier = Modifier.padding(top = 6.dp)
            )
            Text(
                text = stringResource(petsTypeButton.stringResId),
                modifier = Modifier.padding(bottom = 4.dp),
                style = petsTypeButtonTextStyle,
                textAlign = TextAlign.Center,
                color = when (petsTypeButton.type == petTypeState) {
                    true -> Color.White
                    false -> Color.Black
                }
            )
        }
    }
}

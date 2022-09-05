package com.example.pets_project.ui.screens.main.adsList.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.ui.theme.primary
import com.example.pets_project.ui.theme.switchFilterAdListButton
import com.example.pets_project.utils.PetTypeCallback

@Composable
fun AdListFilterRow(
    petState: PetType,
    onClick: PetTypeCallback
) {
    Column(
        modifier = Modifier.background(primary).height(64.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            PetType.values().forEach {
                PetFilterButton(
                    petState = petState,
                    petType = it,
                    onClick = onClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 13.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            PetType.values().forEach {
                Divider(
                    modifier = Modifier
                        // .fillMaxWidth()
                        .weight(1.0f)
                        .height(
                            when (petState == it) {
                                true -> 5.dp
                                false -> 1.dp
                            }
                        )
                        .background(Color.White)
                )
            }
        }
    }
}

@Composable
fun PetFilterButton(
    petState: PetType,
    petType: PetType,
    onClick: PetTypeCallback,
    modifier: Modifier = Modifier,
) {
    val idStringButton = when (petType) {
        PetType.All -> R.string.button_pet_type_all
        PetType.Cat -> R.string.button_pet_type_cat
        PetType.Dog -> R.string.button_pet_type_dog
        PetType.Other -> R.string.button_pet_type_other
    }

    ClickableText(
        text = AnnotatedString(stringResource(id = idStringButton)),
        onClick = { onClick(petType) },
        style = switchFilterAdListButton,
        modifier = modifier
    )
}

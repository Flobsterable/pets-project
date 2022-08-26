package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.login.view.SingleTextField
import com.example.pets_project.ui.screens.login.view.CustomTextField
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.ui.screens.main.addAd.model.PetsTypeButtonItem
import com.example.pets_project.utils.EditTextErrorState
import com.example.pets_project.viewModels.AddAdViewModel

@Composable
fun AdDescriptionColumn(addAdViewModel: AddAdViewModel) {

    val viewState = addAdViewModel.viewState

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Row(
            modifier = Modifier
                .padding(top = 24.dp, start = 31.dp, end = 31.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PetsTypeButton(
                onClick = { addAdViewModel.obtainEvent(AddAdEvent.TypePetChanged(it)) },
                petsTypeButton = PetsTypeButtonItem.CatTypeButtonItem,
                petTypeState = viewState.value!!.petType
            )
            PetsTypeButton(
                onClick = { addAdViewModel.obtainEvent(AddAdEvent.TypePetChanged(it)) },
                petsTypeButton = PetsTypeButtonItem.DogTypeButtonItem,
                petTypeState = viewState.value!!.petType
            )
            PetsTypeButton(
                onClick = { addAdViewModel.obtainEvent(AddAdEvent.TypePetChanged(it)) },
                petsTypeButton = PetsTypeButtonItem.OtherTypeButtonItem,
                petTypeState = viewState.value!!.petType
            )
        }
        SingleTextField(
            modifier = Modifier.padding(top = 32.dp),
            value = viewState.value!!.adName,
            onValueChange = { addAdViewModel.obtainEvent(AddAdEvent.NameAdChanged(it)) },
            placeholderIdString = R.string.edit_text_ad_name,
            errorState = EditTextErrorState.None,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            modifier = Modifier.padding(top = 16.dp),
            height = 254.dp,
            value = viewState.value!!.adDescription,
            onValueChange = { addAdViewModel.obtainEvent(AddAdEvent.DescriptionAdChanged(it)) },
            placeholderIdString = R.string.edit_text_ad_description,
            errorState = EditTextErrorState.None,
            keyboardType = KeyboardType.Text
        )

        MarkButton(
            onClick = { },
            modifier = Modifier.padding(bottom = 24.dp),
            stringResId = R.string.button_place_ad,
            painterResId = R.drawable.ic_done,
            contentDescriptionResId = R.string.cd_add_ad_push_ad
        )
    }
}

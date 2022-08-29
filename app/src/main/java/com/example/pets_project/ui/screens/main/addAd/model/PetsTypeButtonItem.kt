package com.example.pets_project.ui.screens.main.addAd.model

import com.example.pets_project.R
import com.example.pets_project.ui.screens.main.model.PetType

sealed class PetsTypeButtonItem(
    val stringResId: Int,
    val painterResId: Int,
    val contentDescriptionResId: Int,
    val type: PetType
) {
    object CatTypeButtonItem : PetsTypeButtonItem(R.string.button_pet_type_cat, R.drawable.ic_pet_cat, R.string.cd_add_ad_pets_type_cat, PetType.Cat)
    object DogTypeButtonItem : PetsTypeButtonItem(R.string.button_pet_type_dog, R.drawable.ic_pet_dog, R.string.cd_add_ad_pets_type_dog, PetType.Dog)
    object OtherTypeButtonItem : PetsTypeButtonItem(R.string.button_pet_type_other, R.drawable.ic_pet_other, R.string.cd_add_ad_pets_type_other, PetType.Other)
}

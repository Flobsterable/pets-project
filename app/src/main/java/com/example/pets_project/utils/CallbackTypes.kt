package com.example.pets_project.utils

import com.example.pets_project.ui.screens.main.addAd.model.AddAdSubState
import com.example.pets_project.ui.screens.main.model.PetType

typealias StringCallback = (String) -> Unit
typealias Callback = () -> Unit
typealias IntCallback = (Int) -> Unit
typealias AddAdChangeCallback = (AddAdSubState?) -> Unit
typealias PetTypeCallback = (PetType) -> Unit

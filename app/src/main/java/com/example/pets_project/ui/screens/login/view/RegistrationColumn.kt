package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.MarkButton
import com.example.pets_project.ui.screens.login.TextFieldColumn

@Composable
fun RegistrationColumn(){
    //заглушка, поменять при привязке к VM
    var value = ""
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextFieldColumn(modifier = Modifier.padding(top = 40.dp),
            placeholderIdString = R.string.edit_text_name,
            errorMessage ="error",
            KeyboardType.Email)

        TextFieldColumn(modifier = Modifier.padding(top = 16.dp),
            placeholderIdString = R.string.edit_text_email,
            errorMessage ="error",
            KeyboardType.Email)

        TextFieldColumn(modifier = Modifier.padding(top = 16.dp),
            placeholderIdString = R.string.edit_text_password,
            errorMessage ="error",
            KeyboardType.Password)

        TextFieldColumn(modifier = Modifier.padding(top = 16.dp),
            placeholderIdString = R.string.edit_text_repeat_password,
            errorMessage ="error",
            KeyboardType.Password)

        MarkButton(modifier = Modifier.padding(top = 24.dp),
            stringResId = R.string.button_registration,
            painterResId = R.drawable.ic_mark)

    }
}
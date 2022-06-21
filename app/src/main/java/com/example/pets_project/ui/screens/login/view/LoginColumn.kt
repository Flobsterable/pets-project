package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.MarkButton
import com.example.pets_project.ui.screens.login.TextFieldColumn
import com.example.pets_project.ui.theme.mulish
import com.example.pets_project.ui.theme.textButton


@Composable
fun LoginColumn(){
    //заглушка, поменять при привязке к VM
    var value = ""
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextFieldColumn(modifier = Modifier.padding(top = 40.dp),
            placeholderIdString = R.string.edit_text_email,
            errorMessage ="error",
            KeyboardType.Email)
        TextFieldColumn(modifier = Modifier.padding(top = 16.dp),
            placeholderIdString = R.string.edit_text_password,
            errorMessage = "error",
            keyboardType = KeyboardType.Password)

        MarkButton(modifier = Modifier.padding(top = 24.dp),
            stringResId = R.string.button_login ,
            painterResId = R.drawable.ic_mark )

        ClickableText(
            onClick = {},
            modifier = Modifier.padding(top = 50.5f.dp, start = 122.dp, end = 122.dp),
            text = AnnotatedString(stringResource(id = R.string.text_button_forgot)),
            style = textButton
        )
    }
}
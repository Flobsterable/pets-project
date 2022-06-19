package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pets_project.ui.theme.mulish
import com.example.pets_project.ui.theme.switchButton

@Composable
fun SwitchLoginStateButton(stringResId: Int, modifier: Modifier = Modifier){

    ClickableText(
        text = AnnotatedString(stringResource(id = stringResId)),
        onClick = {},
        modifier = modifier.fillMaxWidth(),
        style = switchButton,
    )

}

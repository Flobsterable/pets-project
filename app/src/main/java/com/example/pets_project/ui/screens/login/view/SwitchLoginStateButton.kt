package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.example.pets_project.utils.IntCallback
import com.example.pets_project.ui.theme.switchButton

@Composable
fun SwitchLoginStateButton(
    stringResId: Int,
    onClick: IntCallback,
    modifier: Modifier = Modifier
) {
    ClickableText(
        text = AnnotatedString(stringResource(id = stringResId)),
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        style = switchButton,
    )
}



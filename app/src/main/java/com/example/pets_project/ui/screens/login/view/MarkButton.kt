package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pets_project.R

@Composable
fun MarkButton(
    onClick : () -> Unit,
    modifier: Modifier,
    stringResId : Int,
    painterResId : Int
){
    Button(onClick = onClick ,
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 32.dp),
            painter = painterResource(id = painterResId),
            contentDescription = stringResource(id = R.string.cd_mark_login_button),
        )
        Spacer(modifier = Modifier.padding(start = 10.dp))
        Text(
            modifier = Modifier.padding(end = 32.dp),
            text = stringResource(id = stringResId),
        )
    }
}
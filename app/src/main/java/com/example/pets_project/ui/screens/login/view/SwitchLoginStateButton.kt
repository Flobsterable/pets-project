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

@Composable
fun SwitchLoginStateButton(stringResId: Int, modifier: Modifier = Modifier){

    ClickableText(
        text = AnnotatedString(stringResource(id = stringResId)),
        onClick = {},
        modifier = modifier.fillMaxWidth(),
        //   .padding(start = 12.dp, bottom = 7.dp),
        //  .align(Alignment.CenterHorizontally),
        style = TextStyle(
            color = MaterialTheme.colors.onSecondary,
            fontFamily = mulish,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center
        ),
    )
//        Spacer(modifier = Modifier.fillMaxWidth().height(5.dp).background(MaterialTheme.colors.primary))
    //Divider(color = MaterialTheme.colors.primary, thickness = 5.dp)

}

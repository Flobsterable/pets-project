package com.example.pets_project.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_project.ui.theme.PetsprojectTheme
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.LoginColumn
import com.example.pets_project.ui.screens.login.view.RegistrationColumn
import com.example.pets_project.ui.screens.login.view.SwitchLoginStateButton
import com.example.pets_project.ui.theme.mulish


@Composable
fun LoginScreen(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ImageHeader()

        SwitchLoginStateRow()
        //LoginColumn()
        RegistrationColumn()

        Spacer(modifier = Modifier.weight(1.0f))
        NoLoginButton()
    }

}


@Composable
fun SwitchLoginStateRow(){
    Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()){
            //.padding(start = 32.dp, end = 32.dp)) {
            SwitchLoginStateButton(stringResId = R.string.switch_login,
                modifier = Modifier.weight(1.0f))
            SwitchLoginStateButton(stringResId = R.string.switch_registration,
                modifier = Modifier.weight(1.0f))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp), verticalAlignment = Alignment.Bottom) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .height(1.dp)
                .background(MaterialTheme.colors.primary))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .height(5.dp)
                .background(MaterialTheme.colors.primary))
        }
    }
}

@Composable
fun ImageHeader(){

    Box(modifier = Modifier
        .fillMaxWidth()
         ){
        Image(painter = painterResource(id = R.drawable.logo_header), contentDescription ="Logo Header",
        modifier = Modifier.padding(top = 56.dp, bottom = 44.dp, start = 104.dp, end = 104.dp))
    }
}

@Composable
fun NoLoginButton(){
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp)) {
        ClickableText(
            onClick = {},
            modifier = Modifier
                .padding(top = 20.dp,bottom = 20.dp),
            text = AnnotatedString(stringResource(id = R.string.text_button_no_login)),
            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                fontFamily = mulish,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        )
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_button),
            contentDescription = stringResource(id = R.string.cd_arrow_no_login_button),
            modifier = Modifier
                .padding(top = 22.dp, bottom = 20.dp)
                .clickable { })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsprojectTheme {
        LoginScreen()
    }
}
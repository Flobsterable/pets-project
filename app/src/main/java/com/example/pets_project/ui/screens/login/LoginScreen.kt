package com.example.pets_project.ui.screens.login

import android.content.res.Resources
import android.widget.EditText
import android.widget.Switch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_project.ui.theme.PetsprojectTheme
import com.example.pets_project.R



@Composable
fun LoginScreen(){

    Column {
        ImageHeader()

        SwitchLoginRow()

        LoginBox()
        //RegistrationBox()

        NoLoginButton()
    }

}
@Preview(showBackground = true)
@Composable
fun SwitchLoginRow(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, end = 32.dp)) {
        //SwitchLoginButton(idName = R.string.switch_login)
        SwitchLoginButton(idName = R.string.switch_registration)
    }
}

@Composable
fun SwitchLoginButton(idName: Int){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 12.dp, bottom = 7.dp),
            text = stringResource(id = idName))
        Divider(color = Color.Blue, thickness = 5.dp)
    }
}

@Composable
fun LoginBox(){
    //заглушка, поменять при привязке к VM
    var value = ""
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(text = stringResource(id = R.string.edit_text_email))},
            maxLines = 1,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(text = stringResource(id = R.string.edit_text_password))},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 114.dp, end = 114.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_mark), contentDescription ="Mark Login Button" )
            Text(text = stringResource(id = R.string.button_login))
        }

        Text(
            modifier = Modifier.padding(top = 52.dp, start = 122.dp, end = 122.dp),
            text = stringResource(id = R.string.text_button_fogot),
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp)
        )
    }
}

@Composable
fun RegistrationBox(){
    //заглушка, поменять при привязке к VM
    var value = ""
    Column(modifier = Modifier.fillMaxWidth()) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 32.dp, end = 32.dp)
                .shadow(10.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(stringResource(id = R.string.edit_text_name))},
            maxLines = 1,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(stringResource(id = R.string.edit_text_email))},
            maxLines = 1,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(stringResource(id = R.string.edit_text_password))},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text(stringResource(id = R.string.edit_text_repeat_password))},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 56.dp, end = 56.dp)
        ) {
            Row(modifier = Modifier.padding(start = 32.dp,end = 32.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_mark), contentDescription ="Registration Button Mark" )
                Spacer(modifier = Modifier.padding(start = 10.dp))
                Text(stringResource(id = R.string.button_registration))
            }
        }

    }
}

@Composable
fun ImageHeader(){

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(140.dp) ){
        Image(painter = painterResource(id = R.drawable.logo_header), contentDescription ="Logo Header",
        modifier = Modifier.padding(top = 56.dp,start = 104.dp, end = 104.dp))
    }
}

@Composable
fun NoLoginButton(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 92.dp, end = 92.dp, bottom = 64.dp)) {
        Text(
            modifier = Modifier.padding(start = 32.dp,top = 20.dp,bottom = 20.dp),
            text = stringResource(id = R.string.text_button_no_login),
            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                fontSize = 16.sp,
                fontWeight = FontWeight(600)
            )
        )
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Image(painter = painterResource(id = R.drawable.ic_arrow_button), contentDescription = "",
        modifier = Modifier.padding(top = 22.dp, bottom = 20.dp))
       

    }
}

/*@Composable
fun TextFieldColumn(textEditField: String, errorMessage: String ){
    TextField(
        modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp),
        value = value,
        onValueChange = {value = it},
        label = {textEditField},
        maxLines = 1,
    )
    Text(text = errorMessage,
    modifier = Modifier.padding(top = 4.dp))
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetsprojectTheme {
        LoginScreen()
    }
}
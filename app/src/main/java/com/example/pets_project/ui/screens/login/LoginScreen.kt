package com.example.pets_project.ui.screens.login

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_project.ui.theme.PetsprojectTheme

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

@Composable
fun SwitchLoginRow(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, end = 32.dp)) {
        Text(text = "Вход")
        Text(text = "Регстрация")
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
            placeholder = {Text("email")},
            maxLines = 1,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text("pass")},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(top = 40.dp, start = 114.dp, end = 114.dp)
        ) {
            Text(stringResource(id = R.string.login_button)

        }

        Text(
            modifier = Modifier.padding(top = 52.dp, start = 122.dp, end = 122.dp),
            text = "забыли пароль",
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
                .padding(top = 40.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text("Имя")},
            maxLines = 1,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text("email")},
            maxLines = 1,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text("pass")},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp),
            value = value,
            onValueChange = {value = it},
            placeholder = {Text("pass")},
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 56.dp, end = 56.dp)
        ) {
            Text("Зарегистрироваться")

        }

    }
}

@Composable
fun ImageHeader(){
    Box(modifier = Modifier
        .background(color = Color.Gray)
        .fillMaxWidth()
        .height(140.dp) )
}

@Composable
fun NoLoginButton(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 92.dp, end = 92.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 84.dp),
            text = "войти позже",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )

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
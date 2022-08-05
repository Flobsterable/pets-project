package com.example.pets_project.ui.screens.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.model.LoginSubState
import com.example.pets_project.utils.IntCallback


@Composable
fun SwitchLoginStateRow(
    loginSubState: LoginSubState,
    clickedLoginText: IntCallback,
    clickedRegistrationText: IntCallback
) {

    Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()){

            SwitchLoginStateButton(
                stringResId = R.string.switch_login,
                onClick = clickedLoginText,
                modifier = Modifier.weight(1.0f))
            SwitchLoginStateButton(
                stringResId = R.string.switch_registration,
                onClick = clickedRegistrationText,
                modifier = Modifier.weight(1.0f))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp),
            verticalAlignment = Alignment.Bottom) {

            when(loginSubState){
                LoginSubState.Login ->{
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .height(5.dp)
                        .background(MaterialTheme.colors.primary))
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .height(1.dp)
                        .background(MaterialTheme.colors.primary))
                }
                LoginSubState.Registration -> {
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .height(1.dp)
                        .background(MaterialTheme.colors.primary))
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .height(5.dp)
                        .background(MaterialTheme.colors.primary))
                }
            }
        }
    }
}





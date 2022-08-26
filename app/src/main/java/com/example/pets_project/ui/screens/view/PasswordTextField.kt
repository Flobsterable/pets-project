package com.example.pets_project.ui.screens.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.theme.editTextBackground
import com.example.pets_project.ui.theme.primary
import com.example.pets_project.utils.Callback
import com.example.pets_project.utils.EditTextErrorState
import com.example.pets_project.utils.StringCallback

@Composable
fun PasswordTextField(
    modifier: Modifier,
    value: String,
    onValueChange: StringCallback,
    onSetVisiblePassword: Callback,
    placeholderIdString: Int,
    errorState: EditTextErrorState,
    errorMessageValid: String = "",
    passwordVisible: Boolean = false
) {

    Column(modifier = modifier) {
        Card(
            border = BorderStroke(
                2.dp,
                when (errorState != EditTextErrorState.None) {
                    true -> MaterialTheme.colors.error
                    false -> Color.Transparent
                }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
                .shadow(10.dp, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = stringResource(id = placeholderIdString),
                        style = editTextBackground
                    )
                },
                textStyle = MaterialTheme.typography.body1,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    backgroundColor = MaterialTheme.colors.background
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = when (passwordVisible) {
                    true -> VisualTransformation.None
                    false -> PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = onSetVisiblePassword,
                        modifier = Modifier.padding(end = 10.dp),
                    ) {
                        Icon(
                            painter = when (passwordVisible) {
                                true -> painterResource(id = R.drawable.ic_password_visible)
                                false -> painterResource(id = R.drawable.ic_password_hide)
                            },
                            contentDescription = "",
                            tint = primary
                        )
                    }
                }

            )
        }

        Text(
            text = when (errorState) {
                EditTextErrorState.None -> ""
                EditTextErrorState.IsEmpty -> stringResource(id = R.string.error_empty)
                EditTextErrorState.IsNotRegex -> ""
                EditTextErrorState.IsNotValid -> errorMessageValid
            },
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 36.dp, top = 4.dp)

        )
    }
}

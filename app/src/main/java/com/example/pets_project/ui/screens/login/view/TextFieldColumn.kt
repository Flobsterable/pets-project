package com.example.pets_project.ui.screens.login.view

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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pets_project.ui.screens.login.model.EditTextErrorState
import com.example.pets_project.ui.theme.editTextBackground
import com.example.pets_project.R

@Composable
fun TextFieldColumn(
    modifier: Modifier,
    value : String,
    onValueChange: (String) -> Unit,
    placeholderIdString: Int,
    errorState: EditTextErrorState,
    errorMessageValid: String = "",
    errorMessageRegex : String = "",
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    visiblePassword: Boolean = true
){
    Column(modifier = modifier) {
        Card(
            border = BorderStroke(2.dp,
                if(errorState!= EditTextErrorState.None){MaterialTheme.colors.error}
                else { Color.Transparent
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
                    Text(text = stringResource(id = placeholderIdString),
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
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                visualTransformation = visualTransformation,

            )
        }

            Text(
                text = when(errorState){
                    EditTextErrorState.None -> ""
                    EditTextErrorState.IsEmpty -> stringResource(id = R.string.error_empty)
                    EditTextErrorState.IsNotRegex -> errorMessageRegex
                    EditTextErrorState.IsNotValid -> errorMessageValid
                                       },
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 36.dp, top = 4.dp)

            )
    }
}
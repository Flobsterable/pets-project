package com.example.pets_project.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pets_project.R
import com.example.pets_project.ui.theme.PetsprojectTheme
import com.example.pets_project.ui.theme.editTextBackground
import com.example.pets_project.ui.theme.mulish

@Preview(showBackground = true)
@Composable
fun DefaultPreviewText() {
    PetsprojectTheme {
        TextFieldColumn(modifier = Modifier.padding(top = 40.dp),
            placeholderIdString = R.string.edit_text_email,
            errorMessage ="error",
            KeyboardType.Email)
    }
}

@Composable
fun TextFieldColumn(modifier: Modifier,
                    placeholderIdString: Int,
                    errorMessage: String,
                    keyboardType: KeyboardType
){
    Column(modifier = modifier) {
        var value = ""
        Card(
            border = BorderStroke(2.dp, MaterialTheme.colors.error),
            modifier = Modifier.fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
                .shadow(10.dp, shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
        ) {
            TextField(
                value = value,
                onValueChange = { value = ""},
                placeholder = {
                    Text(text = stringResource(id = placeholderIdString),
                            style = editTextBackground
                    )
                },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    backgroundColor = MaterialTheme.colors.background
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            )
        }

        if(true) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 36.dp, top = 4.dp)
            )

        }

    }
}
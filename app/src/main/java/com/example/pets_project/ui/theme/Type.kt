package com.example.pets_project.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pets_project.R
import com.example.pets_project.ui.theme.Typography

// Set of Material typography styles to start with
val mulish = FontFamily(
    Font(R.font.mulish_black, weight = FontWeight.Black),
    Font(R.font.mulish_black_italic,weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.mulish_bold, weight = FontWeight.Bold),
    Font(R.font.mulish_bold_italic,weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.mulish_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.mulish_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.mulish_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.mulish_extra_light_italic,weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(R.font.mulish_italic, style = FontStyle.Italic),
    Font(R.font.mulish_light, weight = FontWeight.Light),
    Font(R.font.mulish_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.mulish_medium, weight = FontWeight.Medium),
    Font(R.font.mulish_medium_italic,weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.mulish_regular),
    Font(R.font.mulish_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.mulish_semi_bold_italic, weight = FontWeight.SemiBold,style = FontStyle.Italic),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    button = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = mulish,
        fontWeight = FontWeight(400),
        fontSize = 12.sp
    )

)

val editTextBackground = TextStyle(
    fontFamily = mulish,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    color = Color(0xFFB3B3B3))

val textButton =TextStyle(
    color = Color.Black,
    fontFamily = mulish,
    fontSize = 16.sp,
    fontWeight = FontWeight.W600
)

val switchButton= TextStyle(
    color = Color.Black,
    fontFamily = mulish,
    fontSize = 16.sp,
    fontWeight = FontWeight(700),
    textAlign = TextAlign.Center
)


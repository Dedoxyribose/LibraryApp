package com.dedoxyribose.library.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dedoxyribose.library.R

val gilroy = FontFamily(
    Font(R.font.gilroy_medium, weight = FontWeight.Medium),
    Font(R.font.gilroy_semibold, weight = FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    h2 = TextStyle(
        fontFamily = gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    h3 = TextStyle(
        fontFamily = gilroy,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    body1 = TextStyle(
        fontFamily = gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    caption = TextStyle(
        fontFamily = gilroy,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
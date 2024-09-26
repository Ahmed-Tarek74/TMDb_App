package com.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.core.presentation.R

var FontFamily.Companion.BOLD: FontFamily
    get() = FontFamily(Font(R.font.barlow_bold))
    set(_) = Unit

var FontFamily.Companion.REGULAR: FontFamily
    get() = FontFamily(Font(R.font.barlow_regular))
    set(_) = Unit

var FontFamily.Companion.LIGHT: FontFamily
    get() = FontFamily(Font(R.font.barlow_light))
    set(_) = Unit

var FontFamily.Companion.SEMI_BOLD: FontFamily
    get() = FontFamily(Font(R.font.barlow_semibold))
    set(_) = Unit

// Set of Material typography styles to start with
@Composable
fun getTypography() = Typography(
    headlineLarge = TextStyle(
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD,
        color = White
    ),
    headlineMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD,
        color = White
    ),
    headlineSmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.BOLD,
        color = White
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.REGULAR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = White
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.REGULAR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = White
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.REGULAR,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = White
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

)
package com.dedoxyribose.library.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple,
    primaryVariant = PurpleLightest,
    secondary = Apricot,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Purple,
    primaryVariant = PurpleLighter,
    secondary = Apricot,
    onPrimary = Color.White

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val extendedLightColors = ExtendedColors(
    primaryText = Purple
)

private val extendedDarkColors = ExtendedColors(
    primaryText = Color.White
)

private val LocalExtendedColors = staticCompositionLocalOf { extendedLightColors }

@Composable
fun LibraryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val extendedColors = if (darkTheme) {
        extendedDarkColors
    } else {
        extendedLightColors
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Purple)

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current

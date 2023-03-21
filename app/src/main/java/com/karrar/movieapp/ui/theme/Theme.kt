package com.karrar.movieapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryBrandColor,
    primaryVariant = LightPrimaryWhiteColor,
    secondaryVariant = LightTernaryWhiteColor,
    background = DarkBackground,
    surface = DarkCardBackground,
    onSurface = LightSecondaryWhiteColor,
)

private val LightColorPalette = lightColors(
    primary = PrimaryBrandColor,
    primaryVariant = DarkPrimaryBlackColor,
    secondaryVariant = DarkTernaryBlackColor,
    background = LightBackground,
    surface = LightCardBackground,
    onSurface = DarkSecondaryBlackColor,
)

@Composable
fun MovieAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
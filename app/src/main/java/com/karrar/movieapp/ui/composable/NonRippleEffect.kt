package com.karrar.movieapp.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.nonRippleEffect(onClick: () -> Unit) = clickable(
    interactionSource = remember { MutableInteractionSource() }, indication = null) {
    onClick()
}


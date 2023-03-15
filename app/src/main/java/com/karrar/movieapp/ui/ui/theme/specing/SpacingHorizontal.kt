package com.karrar.movieapp.ui.ui.theme.specing

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacingTooSmallHorizontal() {
    Spacer(modifier = Modifier.width(4.dp))
}

@Composable
fun SpacingSmallHorizontal() {
    Spacer(modifier = Modifier.width(8.dp))
}

@Composable
fun SpacingMediumHorizontal() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
fun SpacingLargeHorizontal() {
    Spacer(modifier = Modifier.width(24.dp))
}

@Composable
fun SpacingExtraLargeHorizontal() {
    Spacer(modifier = Modifier.width(32.dp))
}


package com.karrar.movieapp.ui.theme.specing

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacingTooSmallVertical() {
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun SpacingSmallVertical() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SpacingMediumVertical() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun SpacingLargeVertical() {
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun SpacingExtraLargeVertical() {
    Spacer(modifier = Modifier.height(32.dp))
}
package com.karrar.movieapp.ui.composple

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.karrar.movieapp.ui.ui.theme.PrimaryBrandColor
import com.karrar.movieapp.ui.ui.theme.Typography

@Composable
fun AppButton(
    titleButton: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            backgroundColor = PrimaryBrandColor
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() }) {
        Text(
            text = titleButton, style = Typography.button
        )
    }

}


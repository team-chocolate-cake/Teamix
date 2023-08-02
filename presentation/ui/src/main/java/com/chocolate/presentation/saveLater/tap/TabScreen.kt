package com.chocolate.presentation.saveLater.tap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.saveLater.saveLaterScreenContent

@Composable
fun TabScreen(
    content: String
) {
    saveLaterScreenContent()
}

@Composable
@Preview
fun PreviewTabScreen() {
    TabScreen(content = "Tab screen")
}
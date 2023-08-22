package com.chocolate.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.Button
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.screens.on_boarding.navigateToOnboarding
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController

@Composable
fun WelcomeScreen() {
    val navController = LocalNavController.current
    WelcomeContent {
        navController.navigateToOnboarding()
    }
}

@Composable
fun WelcomeContent(
    navigateToOnBoarding: () -> Unit
) {
    val colors = MaterialTheme.customColors()
    Column(modifier = Modifier.fillMaxSize().background(colors.background)) {
        Text(
            modifier = Modifier.padding(top = 64.dp, start = 16.dp, end = 16.dp),
            text = buildAnnotatedString {
                append("Messaging App\n")
                withStyle(style = SpanStyle(color = colors.primary)) {
                    append("but build for \nboost Productivity")
                }
            },
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground87,
            fontSize = 24.sp
        )
        FitImage(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            drawableResource = R.drawable.img_welcome,
            contentDescription = "Welcome image"
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = { navigateToOnBoarding() },
                colors = colors,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Get Stated",
                    style = MaterialTheme.typography.bodyLarge,
                    color = colors.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    TeamixTheme {
        WelcomeScreen()
    }
}

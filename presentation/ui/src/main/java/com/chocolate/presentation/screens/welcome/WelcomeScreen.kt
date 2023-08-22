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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.screens.on_boarding.navigateToOnboarding
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space64
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.TextSize24
import com.chocolate.presentation.theme.customColors

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    WelcomeContent {
        navController.navigateToOnboarding()
    }
}

@Composable
fun WelcomeContent(
    navigateToOnBoarding: () -> Unit
) {
    val colors = MaterialTheme.customColors()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        Text(
            modifier = Modifier.padding(top = Space64, start = Space16, end = Space16),
            text = buildAnnotatedString {
                append(stringResource(R.string.welcome_text))
                withStyle(style = SpanStyle(color = colors.primary)) {
                    append(stringResource(R.string.welcome_content))
                }
            },
            style = MaterialTheme.typography.titleLarge,
            color = colors.onBackground87,
            fontSize = TextSize24
        )
        FitImage(
            modifier = Modifier
                .padding(Space16)
                .fillMaxWidth(),
            image = painterResource(id = R.drawable.img_welcome),
            contentDescription = stringResource(R.string.welcome_image)
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            TeamixButton(
                onClick = { navigateToOnBoarding() },
                colors = colors,
                modifier = Modifier
                    .padding(start = Space16, end = Space16, bottom = Space16)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.get_stated),
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
        WelcomeScreen(navController = rememberNavController())
    }
}

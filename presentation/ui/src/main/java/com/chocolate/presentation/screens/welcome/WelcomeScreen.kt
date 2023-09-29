package com.chocolate.presentation.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.onboarding.navigateToOnboarding
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingMegaGigantic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.TextSize24
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController

@Composable
fun WelcomeScreen() {
    val navController = LocalNavController.current
    WelcomeContent { navController.navigateToOnboarding() }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeContent(
    navigateToOnBoarding: () -> Unit,
) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(
                    top = SpacingMegaGigantic,
                    start = SpacingXLarge,
                    end = SpacingXLarge
                ),
                text = buildAnnotatedString {
                    append(stringResource(R.string.welcome_text))
                    withStyle(style = SpanStyle(color = colors.primary)) {
                        append(stringResource(R.string.welcome_content))
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                color = colors.onBackground87,
                fontSize = TextSize24,
                lineHeight = TextSize24,
            )
            FitImage(
                modifier = Modifier
                    .padding(SpacingXLarge)
                    .wrapContentSize(),
                image = painterResource(id = R.drawable.img_welcome),
                contentDescription = stringResource(R.string.welcome_image)
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.BottomCenter
            ) {
                TeamixButton(
                    onClick = { navigateToOnBoarding() },
                    colors = colors,
                    modifier = Modifier
                        .padding(
                            start = SpacingXLarge,
                            end = SpacingXLarge,
                            bottom = SpacingExtraHuge
                        )
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
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    TeamixTheme {
        WelcomeScreen()
    }
}

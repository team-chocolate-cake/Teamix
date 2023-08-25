package com.chocolate.presentation.screens.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.on_boarding.navigateToOnboarding
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space64
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.TextSize24
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.welcome.WelcomeViewModel

@Composable
fun WelcomeScreen(welcomeViewModel: WelcomeViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by welcomeViewModel.state.collectAsState()
    if (state.onboardingState) {
        navController.navigateToOrganizationName()
    } else {
        WelcomeContent { navController.navigateToOnboarding() }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeContent(
    navigateToOnBoarding: () -> Unit
) {
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        isDarkMode = isSystemInDarkTheme(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxSize(), contentAlignment = Alignment.BottomCenter
            ) {
                TeamixButton(
                    onClick = { navigateToOnBoarding() },
                    colors = colors,
                    modifier = Modifier
                        .padding(start = Space16, end = Space16, bottom = Space32)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.get_stated),
                        style = MaterialTheme.typography.bodyLarge,
                        color = colors.onPrimary
                    )
                }
            }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
            ) {
                item {
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
                            .wrapContentSize(),
                        image = painterResource(id = R.drawable.img_welcome),
                        contentDescription = stringResource(R.string.welcome_image)
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

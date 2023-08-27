package com.chocolate.presentation.screens.on_boarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.FitImage
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.customColors


@Composable
fun PagerScreen(
    onBoardingPage: OnboardingPage, contentDescription: String
) {
    Column(Modifier.fillMaxSize()) {
        FitImage(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            image = painterResource(id = onBoardingPage.imageResource),
            contentDescription = contentDescription
        )
        Text(
            modifier = Modifier.padding(top = SpacingExtraHuge, start = SpacingXXLarge, end = SpacingXXLarge),
            text = onBoardingPage.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.customColors().onBackground87
        )
        Text(
            modifier = Modifier.padding(top = SpacingXLarge, start = SpacingXXLarge, end = SpacingXXLarge),
            text = onBoardingPage.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.customColors().onBackground60
        )
    }
}

@Preview
@Composable
fun OnboardingOne() {
    PagerScreen(OnboardingPage.First, stringResource(R.string.onboarding_image))
}

@Preview
@Composable
fun OnboardingTwo() {
    PagerScreen(OnboardingPage.Second, stringResource(R.string.onboarding_image))
}

@Preview
@Composable
fun OnboardingThree() {
    PagerScreen(OnboardingPage.Third, stringResource(R.string.onboarding_image))
}
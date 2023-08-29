package com.chocolate.presentation.screens.on_boarding

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.composable.TeamixScaffold
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.presentation.util.CollectUiEffect
import com.chocolate.presentation.util.LocalNavController
import com.chocolate.viewmodel.onboarding.OnboardingInteraction
import com.chocolate.viewmodel.onboarding.OnboardingUiEffect
import com.chocolate.viewmodel.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    CollectUiEffect(onboardingViewModel.effect) { effect ->
        when (effect) {
            OnboardingUiEffect.NavigateToOrganizationName ->
                navController.navigateToOrganizationName()
        }
    }
    OnboardingContent(onboardingViewModel)
}

val onboardingPages = listOf(OnboardingPage.First, OnboardingPage.Second, OnboardingPage.Third)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    onboardingInteraction: OnboardingInteraction
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val colors = MaterialTheme.customColors()
    TeamixScaffold(
        isDarkMode =false
     ) {
        Column(  Modifier.fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                pageCount = onboardingPages.size,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(
                    onBoardingPage = onboardingPages[position],
                    stringResource(R.string.onboarding_image)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(horizontal = SpacingXXLarge, vertical = SpacingExtraHuge)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PageIndicator(
                    numberOfPages = onboardingPages.size,
                    selectedPage = pagerState.currentPage
                )
                TeamixButton(
                    onClick = {
                        if (pagerState.currentPage != 2) {
                            coroutineScope.launch {
                                pagerState.scrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onboardingInteraction.onClickLetsStart()
                        }
                    },
                    colors = colors
                ) {
                    Text(
                        text = if (pagerState.currentPage != 2) stringResource(R.string.next) else stringResource(
                            R.string.lets_start
                        ),
                        style = MaterialTheme.typography.titleMedium,
                        color = colors.onPrimary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    TeamixTheme {
        OnboardingScreen()
    }
}
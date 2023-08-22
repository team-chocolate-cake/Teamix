package com.chocolate.presentation.screens.on_boarding

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chocolate.presentation.R
import com.chocolate.presentation.composable.TeamixButton
import com.chocolate.presentation.screens.organiztion.navigateToOrganizationName
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.onboarding.OnboardingEffect
import com.chocolate.viewmodel.onboarding.OnboardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    OnboardingContent(
        navigateToOrganization = { navController.navigateToOrganizationName() },
        onboardingViewModel
    )
}

val onboardingPages = listOf(OnboardingPage.First, OnboardingPage.Second, OnboardingPage.Third)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingContent(
    navigateToOrganization: () -> Unit,
    onboardingEffect: OnboardingEffect
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val colors = MaterialTheme.customColors()
    Scaffold(  modifier = Modifier.fillMaxSize(),
        containerColor = colors.background,
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(horizontal = Space24, vertical = Space32)
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
                            onboardingEffect.onClickNext()
                            navigateToOrganization()
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
        }) {
        Column(
            Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
            ) {
                item {
                    HorizontalPager(
                         modifier = Modifier.weight(8f),
                        pageCount = onboardingPages.size,
                        state = pagerState,
                        verticalAlignment = Alignment.Top
                    ) { position ->
                        PagerScreen(onBoardingPage = onboardingPages[position])
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    TeamixTheme {
        OnboardingScreen(navController = rememberNavController())
    }
}


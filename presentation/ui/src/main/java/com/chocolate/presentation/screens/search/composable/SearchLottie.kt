package com.chocolate.presentation.screens.search.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun SearchLottie(
    modifier: Modifier = Modifier,
    isShow: Boolean,
    isDarkMode: Boolean,
    isPlaying: Boolean = true,
    backgroundColor: Color = MaterialTheme.customColors().background
    ) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.search))
    animateLottieCompositionAsState(composition = composition, isPlaying = isPlaying)
    AnimatedVisibility(visible = isShow) {
        Column(
            modifier = modifier.background(backgroundColor),
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                modifier = Modifier.fillMaxWidth().height(350.dp),
                composition = composition,
                isPlaying = isPlaying,
                alignment = Alignment.Center
            )
        }
    }
}
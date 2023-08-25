package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.customColors

@Composable
fun NoInternetLottie(
    text: String,
    modifier: Modifier = Modifier,
    isShow: Boolean,
    isDarkMode: Boolean,
    onClickRetry: () -> Unit = {}
) {
    val animationResId = if (isDarkMode)  R.raw.animation_no_internet_dark else R.raw.animation_no_internet
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationResId))
    animateLottieCompositionAsState(composition = composition, isPlaying = false)
    val color = MaterialTheme.customColors()
    AnimatedVisibility(visible = isShow) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color.background),
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                modifier = Modifier.size(350.dp),
                composition = composition,
                isPlaying = false
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = text, color = color.onBackground87)
                Text(
                    text = stringResource(R.string.retry),
                    color = color.primary,
                    modifier = Modifier.clickable { onClickRetry() })
            }
        }
    }
}
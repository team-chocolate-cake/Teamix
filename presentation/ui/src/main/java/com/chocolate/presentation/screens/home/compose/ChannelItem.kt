package com.chocolate.presentation.screens.home.compose

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.CustomColorsPalette
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.viewmodel.home.ChannelUiState

@SuppressLint("RememberReturnType")
@Composable
fun ChannelItem(
    state: ChannelUiState,
    colors: CustomColorsPalette,
    onClickItemChannel: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    val animateIcon by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(animationSpec = tween(durationMillis = 300))
            .clip(RoundedCornerShape(12.dp))
            .background(color = colors.card)
            .padding(Space16), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconsChannel =
                if (state.isPrivateChannel) R.drawable.ic_lock else R.drawable.ic_hashtag
            Icon(
                painter = painterResource(id = iconsChannel),
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.labelLarge,
                color = colors.onBackground87
            )
            Spacer(modifier = Modifier.weight(1f))
            if (state.topics.isNotEmpty()){
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = colors.onBackground60,
                    modifier = Modifier
                        .rotate(animateIcon)
                        .clickable { isExpanded = !isExpanded }
                )
            }
        }
        if (isExpanded) {
            Log.d("TAG", "ChannelItem:${state.topics} ")
            state.topics.forEach { topicUIState ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            Toast
                                .makeText(context, "navigate to ${state.name}", Toast.LENGTH_SHORT)
                                .show()
                        },
                    verticalArrangement = Arrangement.Center
                ) {
                    Divider(modifier = Modifier.padding(Space8), color = colors.border)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .pointerInput(Unit) {
                                detectTapGestures(onPress = {
                                    onClickItemChannel(state.channelId)
                                    Toast
                                        .makeText(
                                            context,
                                            state.topics.first().name,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                })
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = topicUIState.name, color = colors.onBackground60)
                        BadgeHome(
                            number = topicUIState.topicBadge,
                            textColor = colors.onPrimary,
                            cardColor = colors.primary
                        )
                    }
                }
            }
        }
    }
}
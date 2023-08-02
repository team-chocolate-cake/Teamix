package com.chocolate.presentation.screens.home.compose

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.home.ChannelUIState
import com.chocolate.presentation.theme.CustomColorsPalette

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemChannel(
    state: ChannelUIState,
    colors: CustomColorsPalette,
    onLongClickChannel: () -> Unit
) {
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    val animate by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                )
            )
            .clip(RoundedCornerShape(12.dp))
            .background(color = colors.onPrimary)
            .padding(16.dp)
            .combinedClickable(
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongClickChannel()
                    Toast
                        .makeText(context, "Long Click", Toast.LENGTH_SHORT)
                        .show()
                },
                onClick = { })
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val iconsChannel =
                if (state.isPrivateChannel) R.drawable.ic_lock else R.drawable.ic_hashtag
            Icon(
                painter = painterResource(id = iconsChannel),
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.labelLarge,
                color = colors.onBackground87
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = colors.onBackground60,
                modifier = Modifier
                    .rotate(animate)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            )

        }
        if (isExpanded) {
            state.topics.forEach { topicUIState ->
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp)
                ) {
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = colors.border
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topicUIState.name,
                            color = colors.onBackground60,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                    }

                }

            }
        }
    }
}
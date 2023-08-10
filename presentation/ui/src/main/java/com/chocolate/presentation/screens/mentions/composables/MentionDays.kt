package com.chocolate.presentation.screens.mentions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.Typography
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.mentions.state.MentionDaysUiState

@Composable
fun MentionDays(mentions: MentionDaysUiState, onClick: () -> Unit) {
    Column(
        modifier = Modifier.wrapContentSize().padding(Space16),
        verticalArrangement = Arrangement.spacedBy(Space8)
    ) {
        Text(mentions.day, style =MaterialTheme.typography.bodyMedium, color = MaterialTheme.customColors().black)
        mentions.mentionInfo.forEach {
            MentionInfo(it, onClick)
        }
    }
}
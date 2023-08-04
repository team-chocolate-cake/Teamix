package com.chocolate.presentation.mentions.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.Typography
import com.chocolate.viewmodel.mentions.state.MentionDaysUiState

@Composable
fun MentionDays(mentions: MentionDaysUiState, onClick: () -> Unit) {
    LazyColumn(userScrollEnabled = false, verticalArrangement = Arrangement.spacedBy(Space8)) {
        item {
            Text(mentions.day, style = Typography.bodyMedium, color = Color(0XFF000000))
        }
        items(mentions.mentionInfo) {
            MentionInfo(it, onClick)
        }
    }
}
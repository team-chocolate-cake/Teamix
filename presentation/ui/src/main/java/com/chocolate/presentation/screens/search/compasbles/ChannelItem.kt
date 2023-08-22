package com.chocolate.presentation.screens.search.compasbles

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.search.ChannelsUiState
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelItem(onClickChannelItem: (Int)-> Unit, state: ChannelsUiState,modifier: Modifier = Modifier) {
    val colors = MaterialTheme.customColors()

    Card(
        onClick = {onClickChannelItem(state.channelId) }, modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Space16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconsChannel =
                if (state.isPrivate) R.drawable.ic_lock else R.drawable.ic_hashtag
            Icon(
                painter = painterResource(id = iconsChannel),
                contentDescription = null,
                tint = colors.primary,
                modifier = Modifier.padding(end = Space8)
            )
            Text(
                text = state.channelName,
                style = MaterialTheme.typography.labelMedium,
                color = colors.onBackground87
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = state.numberOfMembers.toString() + " member",
                style = MaterialTheme.typography.labelSmall,
                color = colors.onBackground60
            )

        }
    }
}
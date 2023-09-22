package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.viewmodel.topicMessages.ReactionUiState
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors

@Composable
fun ReactionButton(
    reaction: ReactionUiState,
    onReactionClicked:(Boolean, ReactionUiState)->Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = SpacingMedium)
            .clip(RoundedCornerShape(SpacingExtraHuge))
            .background(
                if (reaction.clicked) MaterialTheme.customColors().gray
                else MaterialTheme.customColors().lightGray
            )
            .clickable {
                onReactionClicked(reaction.clicked , reaction)
            }
            .padding(vertical = SpacingMedium, horizontal = SpacingXMedium)
    ) {
        Row() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(reaction.reaction).build(),
                contentDescription = "Reaction",
                modifier = Modifier
                    .size(SpacingXLarge)
                    .padding(end = SpacingMedium)
            )
            Text(
                text = reaction.count.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.customColors().onBackground87
            )
        }
    }
}
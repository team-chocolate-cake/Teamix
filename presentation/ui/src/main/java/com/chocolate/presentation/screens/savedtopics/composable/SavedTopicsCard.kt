package com.chocolate.presentation.screens.savedtopics.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.SpacingGigantic
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.SpacingXSmall
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.savedTopics.SavedTopicsItemUiState

@Composable
fun SavedTopicsCard(
    topic: SavedTopicsItemUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Radius12))
            .background(color = MaterialTheme.customColors().card)
            .padding(SpacingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row (modifier = Modifier.fillMaxWidth()){
            Image(
                painter = rememberAsyncImagePainter(model = topic.creatorImage),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = SpacingMedium, vertical = SpacingMedium)
                    .size(SpacingGigantic)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Column {
                Row {
                    Text(
                        text = topic.creatorName,
                        color = MaterialTheme.customColors().primary,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(start = SpacingMedium),
                        text = topic.sentTime,
                        color = MaterialTheme.customColors().onBackground87,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

                Text(
                    modifier = Modifier.padding(
                        top = SpacingXSmall,
                        start = SpacingSmall,
                        end = SpacingSmall,
                    ),
                    text = topic.topicContent,
                    color = MaterialTheme.customColors().onBackground87,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines=2
                )
            }

        }
    }
}

@Composable
@Preview(showSystemUi = true, backgroundColor = 0x80000000, showBackground = true)
fun SavedTopicsCardReview() {
        SavedTopicsCard(topic = SavedTopicsItemUiState())
}
package com.chocolate.presentation.screens.channel.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun Topic(
    topicName: String = "Chat",
    repliesCount: Int = 65,
    viewAllClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            color = MaterialTheme.customColors().onSecondary,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = topicName,
                    color = Color.White, //todo this color must be changed for dark theme
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "$repliesCount replies",
                    color = Color.White, //todo this color must be changed for dark theme
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Surface(
            color = Color.White,//todo this color must be changed for dark theme
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                ReplyMessage()
                Spacer(modifier = Modifier.height(Space8))
                ReplyMessage()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = Space8, end = Space8, top = Space16, bottom = Space8)
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                        .background(MaterialTheme.customColors().lightGray)
                        .clickable { viewAllClick() }
                ) {
                    Text(
                        text = "View All",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Space8),
                        color = MaterialTheme.customColors().primary,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

@Composable
@Preview(showSystemUi = true)
fun TopicReview() {
    TeamixTheme {
        Topic(
            viewAllClick = {}
        )
    }
}
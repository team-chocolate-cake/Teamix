package com.chocolate.presentation.screens.channel.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun Topic(
    topicName: String,
    onSeeAll: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.customColors().card,
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onSeeAll(topicName) }
            .padding(SpacingXMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier.padding(vertical = SpacingXLarge , horizontal = SpacingSmall),
            text = topicName,
            color = MaterialTheme.customColors().onBackground87,
            style = MaterialTheme.typography.bodyMedium
        )

        Icon(
            tint = MaterialTheme.customColors().onBackground87,
            modifier = Modifier
                .size(24.dp)
                .rotate(180f),
            painter = painterResource(id = R.drawable.alt_arrow_left),
            contentDescription = topicName
        )
    }
}

@Composable
@Preview(showSystemUi = true, backgroundColor = 0x80000000, showBackground = true)
fun TopicReview() {
    TeamixTheme {
        Topic(
            topicName = "Test Topic Name",
            onSeeAll = {}
        )
    }
}
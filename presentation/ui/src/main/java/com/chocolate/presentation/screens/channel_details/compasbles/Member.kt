package com.chocolate.presentation.screens.channel_details.compasbles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.customColors

@Composable
fun Member() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.person).build(),
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .width(50.dp)
                .padding(top = Space4),
            textAlign = TextAlign.Center,
            text = "Ahmed Mimo",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.customColors().onBackground87
        )

    }
}
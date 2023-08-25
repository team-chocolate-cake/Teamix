package com.chocolate.presentation.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space1
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberSearchItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    id: Int,
    label: String = "",
    isOnline: Boolean,
    onClickMemberItem: (Int) -> Unit,
) {
    val colors = MaterialTheme.customColors()
    Card(
        onClick = { onClickMemberItem(id) }, modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        shape = RoundedCornerShape(Radius12)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Space8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .width(45.dp)
                .wrapContentHeight()) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                val borderColor by animateColorAsState(
                    targetValue = if (isOnline) colors.card else colors.onBackground60,
                    label = label
                )
                val circleColor by animateColorAsState(
                    targetValue = if (isOnline) colors.green else colors.card,
                    label = label
                )
                Card(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd),
                    border = BorderStroke(width = Space1, color = borderColor),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = circleColor)
                ) {}
            }
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                color = colors.onBackground87,
                modifier = Modifier.padding(start = Space8)
            )
        }
    }
}
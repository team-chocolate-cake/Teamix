package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Border1
import com.chocolate.presentation.theme.ButtonSize24
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.ImageSize40
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.Space0
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors

@Composable
fun PersonCardWithDetails(
    modifier: Modifier = Modifier,
    painter: Painter,
    personImageUrl: String,
    title: String,
    subTitle: String,
    contentDescription: String?=null,
    subTitleMaxLine: Int = 1,
    date: String = "",
    isSelected: Boolean = false
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Radius12),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.customColors().card),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = Space0),
        border = if (isSelected) {
            BorderStroke(Border1, color = MaterialTheme.customColors().primary)
        } else {
            CardDefaults.outlinedCardBorder(enabled = false)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(Space8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(personImageUrl),
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(ImageSize40)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(horizontal = Space8),
                verticalArrangement = Arrangement.spacedBy(Space8)
            ) {
                Row(
                    modifier = if (!isSelected) { Modifier.fillMaxWidth() } else { Modifier },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.customColors().onBackground87
                    )
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.customColors().onBackground60
                    )
                }
                Text(
                    text = subTitle,
                    maxLines = subTitleMaxLine,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.customColors().onBackground60
                )
            }

            Spacer(modifier = Modifier.weight(Float1))
            AnimatedVisibility (isSelected) {
                CircularButton(
                    containerColor = MaterialTheme.customColors().primary,
                    size = ButtonSize24
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painter,
                        tint = MaterialTheme.customColors().onPrimary,
                        contentDescription = contentDescription
                    )
                }
            }
        }
    }
}

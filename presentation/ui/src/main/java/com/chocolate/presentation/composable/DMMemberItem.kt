package com.chocolate.presentation.composable


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Float1
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.SpacingMassive
import com.chocolate.presentation.theme.SpacingSmall
import com.chocolate.presentation.theme.SpacingTiny
import com.chocolate.presentation.theme.SpacingUltraGigantic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.dm_choose_member.DMChooseMembersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DMMemberItem(
    modifier: Modifier = Modifier,
    animateLabel: String = "",
    chooseMemberUiState: DMChooseMembersUiState,
    painter: Painter,
    contentDescription: String = "",
    onClickMemberItem: (String) -> Unit
) {

    val colors = MaterialTheme.customColors()
    val cardBorderColor by animateColorAsState(
        targetValue = if (chooseMemberUiState.isSelected) colors.primary else Color.Unspecified,
        label = animateLabel
    )

    val cardBorderWidth by animateDpAsState(
        targetValue = if (chooseMemberUiState.isSelected) SpacingSmall else SpacingTiny,
        label = animateLabel
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(SpacingUltraGigantic)
            .padding(horizontal = SpacingXLarge),
        colors = CardDefaults.cardColors(colors.card),
        shape = RoundedCornerShape(Radius12),
        onClick = { onClickMemberItem(chooseMemberUiState.userId) },
        border = BorderStroke(width = cardBorderWidth, color = cardBorderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpacingXMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = chooseMemberUiState.imageUrl),
                contentDescription = contentDescription,
                modifier = Modifier
                    .size(SpacingMassive)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = SpacingXMedium),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = chooseMemberUiState.name,
                    color = colors.onBackground87,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.weight(Float1))
            AnimatedVisibility(visible = chooseMemberUiState.isSelected) {
                CircularButton(containerColor = colors.primary, size = SpacingXLarge) {
                    Icon(
                        painter = painter,
                        tint = colors.border,
                        contentDescription = contentDescription,
                    )
                }
            }
        }
    }
}
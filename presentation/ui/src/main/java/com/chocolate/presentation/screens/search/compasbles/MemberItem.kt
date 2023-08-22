package com.chocolate.presentation.screens.search.compasbles

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.search.MembersUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberItem(onClickMemberItem: (Int) -> Unit, state: MembersUiState,modifier: Modifier = Modifier) {
    val colors = MaterialTheme.customColors()
    Card(
        onClick = { onClickMemberItem(state.memberId)}, modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = CardDefaults.cardColors(containerColor = colors.card),
        shape = RoundedCornerShape(12.dp)
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
                    painter = rememberAsyncImagePainter(model = state.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Card(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd),
                    border = BorderStroke(width = 1.dp, color = colors.card),
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = colors.green)
                ) {}
            }
            Text(
                text = state.memberName,
                style = MaterialTheme.typography.labelMedium,
                color = colors.onBackground87,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
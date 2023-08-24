package com.chocolate.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Radius12
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.chooseMember.SelectedMembersUiState
@Composable
fun SelectedMemberItem(modifier: Modifier = Modifier,selectedMembersUiState: SelectedMembersUiState) {
    val colors = MaterialTheme.customColors()
    Box(modifier = modifier.wrapContentSize()) {
        Card(
            colors = CardDefaults.cardColors(colors.card),
            shape = RoundedCornerShape(Radius12),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(colors.red60),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cancel),
                    tint = colors.border,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Image(
                painter = rememberAsyncImagePainter(model = selectedMembersUiState.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Text(
                text = selectedMembersUiState.name,
                style = MaterialTheme.typography.labelSmall,
                color = colors.onBackground87,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 4.dp, bottom = 8.dp)
            )
        }
    }
}
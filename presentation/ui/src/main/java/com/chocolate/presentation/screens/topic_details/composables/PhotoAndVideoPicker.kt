package com.chocolate.presentation.screens.topic_details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.viewmodel.topic.PhotoOrVideoUiState
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space56
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun PhotoAndVideoPicker(
    onClickCamera:()->Unit,
    onClickPhotoOrVideo:(Int)->Unit,
    photoOrVideoList: List<PhotoOrVideoUiState> = emptyList(),
) {
    LazyRow(
        modifier = Modifier
            .padding(start = Space16 , top = Space16 , bottom = Space16 ),
        horizontalArrangement = Arrangement.spacedBy(Space8)
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = Space8)
                    .size(Space56)
                    .clip(RoundedCornerShape(Space8 + Space4))
                    .background(MaterialTheme.customColors().primary)
                    .clickable {
                        onClickCamera()
                    },

                ) {

                Icon(
                    modifier = Modifier.size(Space24),
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "",
                    tint = MaterialTheme.customColors().onPrimary
                )
            }
        }
        items(photoOrVideoList.size) {
            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .size(Space56)
                    .clip(RoundedCornerShape(Space8 + Space4))
                    .clickable {
                        onClickPhotoOrVideo(it)
                    }
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(photoOrVideoList[it].file)
                        .build(),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = ""
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = Space4 , end = Space4)
                        .size(10.dp)
                        .clip(CircleShape)
                        .border(width = if(photoOrVideoList[it].isSelected)0.dp else (0.5).dp, color = Color.White)
                        .background(if(photoOrVideoList[it].isSelected)MaterialTheme.customColors().primary else Color.Transparent)
                ){
                    if(photoOrVideoList[it].isSelected)
                    Icon(
                        painter = painterResource(id = R.drawable.selected),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }

    }

}

@Composable
@Preview
fun PhotoAndVideoPickerPreview() {
    TeamixTheme() {
        PhotoAndVideoPicker( {} , {},emptyList())
    }
}
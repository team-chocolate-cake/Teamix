package com.chocolate.presentation.screens.topic_details.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel.composables.BottomSheetItem
import com.chocolate.presentation.screens.topic_details.PhotoOrVideoUiState
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.customColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentBottomSheet(
    onClickCamera:()->Unit,
    onClickPhotoOrVideo:(Int)->Unit,
    photoOrVideoList: List<PhotoOrVideoUiState> = emptyList(),
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        containerColor = MaterialTheme.customColors().background,
        onDismissRequest = {
            onDismiss()
        },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(start = Space16),
                text = "Photo & videos",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.customColors().onBackground87
            )
            PhotoAndVideoPicker(
                onClickCamera = onClickCamera,
                onClickPhotoOrVideo = onClickPhotoOrVideo,
                photoOrVideoList = photoOrVideoList
            )
            BottomSheetItem(
                icon = R.drawable.video_record,
                text = "Record a video"
            )
            BottomSheetItem(
                icon = R.drawable.file,
                text = "Upload a file"
            )
        }
    }
}
package com.chocolate.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.topic.PhotoOrVideoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttachmentBottomSheet(
    onClickCamera: () -> Unit,
    onClickPhotoOrVideo: (Int) -> Unit,
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
                    .padding(start = SpacingXLarge),
                text = stringResource(R.string.photo_videos),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.customColors().onBackground87
            )
            PhotoAndVideoPicker(
                onClickCamera = onClickCamera,
                onClickPhotoOrVideo = onClickPhotoOrVideo,
                photoOrVideoList = photoOrVideoList
            )
            BottomSheetItem(
                icon = painterResource(id = R.drawable.video_record),
                text = stringResource(R.string.record_a_video)
            )
            BottomSheetItem(
                icon = painterResource(id = R.drawable.file),
                text = stringResource(R.string.upload_a_file)
            )
        }
    }
}
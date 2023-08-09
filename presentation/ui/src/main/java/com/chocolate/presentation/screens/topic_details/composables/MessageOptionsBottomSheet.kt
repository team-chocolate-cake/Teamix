package com.chocolate.presentation.screens.topic_details.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel.composables.BottomSheetItem
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageOptionsBottomSheet(
    onAddReactionToMessage: (Int) -> Unit,
    onSaveMessage: () -> Unit,
    onGetNotification: () -> Unit,
    onPinMessage: () -> Unit,
    onDismiss: () -> Unit,

) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val reacts = listOf(
        R.drawable.red_heart,
        R.drawable.clapping_hands,
        R.drawable.crying_face,
        R.drawable.face_with_smile,
        R.drawable.hushed_face,
        R.drawable.thinking_face,
        R.drawable.thumbs_down,
        R.drawable.thumbs_up,
        R.drawable.partying_face,
    )
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column() {
            LazyRow(
                modifier = Modifier
                    .padding(Space16),
                horizontalArrangement = Arrangement.spacedBy(Space16),
            ) {
                items(reacts.size) {
                    AsyncImage(
                        alignment = Alignment.Center,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(reacts[it]).build(),
                        contentDescription = "Reaction",
                        modifier = Modifier
                            .size(Space32)
                            .clip(CircleShape)
                            .clickable {
                                onAddReactionToMessage(reacts[it])
                            }
                            .padding(end = Space4)
                    )
                }
            }

            BottomSheetItem(
                icon = R.drawable.bookmark,
                text = "Add to saved items" ,
                onClickItem = {
                    onSaveMessage()
                }
            )
            BottomSheetItem(
                icon = R.drawable.notification_notes,
                text = "Get notified about new replies" ,
                onClickItem = {
                    onGetNotification()
                }
            )
            BottomSheetItem(
                icon = R.drawable.bin_message,
                text = "Pin to conversation" ,
                onClickItem = {
                    onPinMessage()
                }
            )
        }
    }


}
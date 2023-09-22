package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingUltraGigantic
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.topicMessages.PhotoOrVideoUiState

@Composable
fun StartNewMessage(
    openEmojisTile: () -> Unit,
    onMessageInputChanged: (String) -> Unit,
    onSendMessage: () -> Unit,
    onStartVoiceRecording: () -> Unit,
    onClickCamera: () -> Unit,
    onClickPhotoOrVideo: (Int) -> Unit,
    modifier: Modifier = Modifier,
    photoOrVideoList: List<PhotoOrVideoUiState> = emptyList(),
    messageInput: String = "",
    contentDescription: String? = null
) {
    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        AttachmentBottomSheet(
            onClickCamera = onClickCamera,
            onClickPhotoOrVideo = onClickPhotoOrVideo,
            photoOrVideoList = photoOrVideoList
        ) {
            showSheet = false
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors().card)
            .padding(SpacingXLarge)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpacingXMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { showSheet = true },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_attachment),
                        contentDescription = contentDescription,
                        tint = MaterialTheme.customColors().onBackground60,
                        modifier = Modifier
                            .size(SpacingExtraHuge),
                    )
                }
                IconButton(
                    onClick = { openEmojisTile() },
                    modifier = Modifier
                        .background(MaterialTheme.customColors().card)
                        .padding(end = SpacingXMedium)
                        .size(SpacingXXLarge)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.smile_circle),
                        contentDescription = contentDescription,
                        tint = MaterialTheme.customColors().onBackground60,
                    )
                }
            }

            TeamixTextField(
                singleLine = true,
                modifier = Modifier
                    .height(SpacingUltraGigantic)
                    .fillMaxWidth(0.9f)
                    .padding(end = 8.dp),
                value = messageInput,
                onValueChange = { onMessageInputChanged(it) },
                containerColor =MaterialTheme.customColors().background
            )
            Surface(
                modifier = Modifier
                    .padding(end = SpacingMedium)
                    .size(SpacingXXLarge)
            ) {
                AnimatedVisibility(visible = !messageInput.isEmpty()) {
                    IconButton(
                        onClick = { onSendMessage() },
                        modifier = Modifier
                            .background(MaterialTheme.customColors().card)
                            .size(SpacingXXLarge),
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.arrow_right
                            ),
                            tint = MaterialTheme.customColors().primary,
                            contentDescription = ""
                        )
                    }
                }
                AnimatedVisibility(visible = messageInput.isEmpty()) {
                    IconButton(
                        onClick = { onStartVoiceRecording() },
                        modifier = Modifier
                            .background(MaterialTheme.customColors().card)
                            .size(SpacingXXLarge),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.microphoneteamix),
                            contentDescription = contentDescription,
                            tint = MaterialTheme.customColors().onBackground60,
                        )
                    }
                }
            }

        }
    }
}

@Composable
@Preview
fun StartNewMessagePreview() {
    TeamixTheme {
        StartNewMessage(
            {}, {}, {}, {}, {}, {}
        )
    }
}
package com.chocolate.presentation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingExtraHuge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingXMedium
import com.chocolate.presentation.theme.SpacingXXLarge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors
import com.chocolate.viewmodel.topic.PhotoOrVideoUiState

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
            .background(MaterialTheme.customColors().background)
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
                Icon(
                    painter = painterResource(id = R.drawable.add_attachment),
                    contentDescription = contentDescription,
                    tint = MaterialTheme.customColors().onBackground60,
                    modifier = Modifier
                        .padding(end = SpacingXMedium + SpacingMedium)
                        .size(SpacingExtraHuge)
                        .clickable {
                            showSheet = true
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.smile_circle),
                    contentDescription = contentDescription,
                    tint = MaterialTheme.customColors().onBackground60,
                    modifier = Modifier
                        .padding(end = SpacingXMedium)
                        .size(SpacingXXLarge)
                        .clickable {
                            openEmojisTile()
                        }
                )
            }
                TeamixTextField(
                    singleLine = false,
                    modifier = Modifier
                        .height(SpacingExtraHuge)
                        .fillMaxWidth(0.9f)
                        .padding(end = 8.dp),
                    value = messageInput,
                    onValueChange = { onMessageInputChanged(it) },
                )
            Surface(
                modifier = Modifier
                    .padding(end = SpacingMedium)
                    .size(SpacingXXLarge)
            ) {
                AnimatedVisibility(visible = !messageInput.isEmpty()) {
                    Icon(
                        modifier = Modifier
                            .size(SpacingXXLarge)
                            .clickable {
                                onSendMessage()
                            },
                        painter = painterResource(
                            id = R.drawable.arrow_right
                        ),
                        tint = MaterialTheme.customColors().primary,
                        contentDescription = ""
                    )
                }
                AnimatedVisibility(visible = messageInput.isEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.microphone),
                        contentDescription = contentDescription,
                        tint = MaterialTheme.customColors().onBackground60,
                        modifier = Modifier
                            .size(SpacingXXLarge)
                            .clickable {
                                onStartVoiceRecording()
                            }
                    )
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
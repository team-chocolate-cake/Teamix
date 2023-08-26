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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space8
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
    photoOrVideoList: List<PhotoOrVideoUiState> = emptyList(),
    modifier: Modifier = Modifier,
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
            .padding(Space16)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Space8),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.add_attachment),
                    contentDescription = contentDescription,
                    tint = MaterialTheme.customColors().onBackground60,
                    modifier = Modifier
                        .padding(end = Space8 + Space4)
                        .size(Space32)
                        .clickable {
                            showSheet = true
                        }
                )

                Icon(
                    painter = painterResource(id = R.drawable.smile_circle),
                    contentDescription = contentDescription,
                    tint = MaterialTheme.customColors().onBackground60,
                    modifier = Modifier
                        .padding(end = Space8)
                        .size(Space24)
                        .clickable {
                            openEmojisTile()
                        }
                )
            }
            Surface(
                shape = RoundedCornerShape(Space8),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(end = Space16),
                color = MaterialTheme.customColors().lightGray
            ) {
                BasicTextField(
                    singleLine = true,
                    modifier = Modifier
                        .height(Space32),
                    value = messageInput,
                    onValueChange = { onMessageInputChanged(it) },
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.padding(Space8)
                        ) {
                            if (messageInput.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.start_new_message),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.customColors().onBackground60
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
            Surface(
                modifier = Modifier
                    .padding(end = Space4)
                    .size(Space24)
            ) {
                AnimatedVisibility(visible = !messageInput.isEmpty()) {
                    Icon(
                        modifier = Modifier
                            .size(Space24)
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
                            .size(Space24)
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
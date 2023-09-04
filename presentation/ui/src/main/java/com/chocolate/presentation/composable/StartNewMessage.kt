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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            Surface(
                shape = RoundedCornerShape(SpacingXMedium),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(end = SpacingXLarge),
                color = MaterialTheme.customColors().background
            ) {
                BasicTextField(
                    singleLine = true,
                    modifier = Modifier
                        .height(SpacingExtraHuge),
                    value = messageInput,
                    onValueChange = { onMessageInputChanged(it) },
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.padding(SpacingXMedium)
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
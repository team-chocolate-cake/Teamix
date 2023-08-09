package com.chocolate.presentation.screens.topic_details.composables

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space24
import com.chocolate.presentation.theme.Space32
import com.chocolate.presentation.theme.Space4
import com.chocolate.presentation.theme.Space64
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun StartNewMessage(
    modifier: Modifier = Modifier
) {
    var message by remember {
        mutableStateOf("")
    }
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        AttachmentBottomSheet(
            {} , {}
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
                    contentDescription = "",
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
                    contentDescription = "",
                    tint = MaterialTheme.customColors().onBackground60,
                    modifier = Modifier
                        .padding(end = Space8)
                        .size(Space24)
                        .clickable {
                            //todo open Emojis tile
                        }
                )
            }
            Surface(
                shape = RoundedCornerShape(Space8),
                modifier = Modifier.fillMaxWidth(0.9f).padding(end = Space16),
                color = MaterialTheme.customColors().lightGray
            ) {
                BasicTextField(
                    singleLine = true,
                    modifier = Modifier
                        .height(Space32),
                    value = message,
                    onValueChange = { message = it },
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.padding(Space8)
                        ) {
                            if (message.isEmpty()) {
                                Text(
                                    text = "Start new message",
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
                modifier = Modifier.padding(end = Space4).size(Space24)
            ) {
                AnimatedVisibility(visible = !message.isEmpty() ) {
                    Icon(
                        modifier = Modifier
                            .size(Space24)
                            .clickable {
                                //todo send message
                            },
                        painter = painterResource(
                            id = R.drawable.arrow_right
                        ),
                        tint = MaterialTheme.customColors().primary,
                        contentDescription = ""
                    )
                }
                AnimatedVisibility(visible =message.isEmpty() ) {
                    Icon(
                        painter = painterResource(id = R.drawable.microphone),
                        contentDescription = "",
                        tint = MaterialTheme.customColors().onBackground60,
                        modifier = Modifier
                            .size(Space24)
                            .clickable {
                                //todo mic
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
        StartNewMessage()
    }
}
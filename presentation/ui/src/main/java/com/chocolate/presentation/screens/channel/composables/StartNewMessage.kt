package com.chocolate.presentation.screens.channel.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.Space2
import com.chocolate.presentation.theme.Space64
import com.chocolate.presentation.theme.Space8
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun StartNewMessage(modifier: Modifier = Modifier) {
    var topic by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors().background)
            .padding(Space16)
    ) {
        Column {
            Surface(
                shape = RoundedCornerShape(Space8),
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.customColors().lightGray
            ) {
                BasicTextField(
                    singleLine = true,
                    modifier = Modifier.padding(vertical = Space8, horizontal = Space16),
                    value = topic,
                    onValueChange = { topic = it },
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            if (topic.isEmpty()) {
                                Text(
                                    text = "Topic name",
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
                shape = RoundedCornerShape(Space8),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Space8),
                color = MaterialTheme.customColors().lightGray
            ) {
                BasicTextField(
                    singleLine = true,
                    modifier = Modifier
                        .height(Space64)
                        .padding(vertical = Space8, horizontal = Space16),
                    value = message,
                    onValueChange = { message = it },
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
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
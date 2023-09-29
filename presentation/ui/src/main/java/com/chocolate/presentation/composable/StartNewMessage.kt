package com.chocolate.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.chocolate.presentation.R
import com.chocolate.presentation.theme.SpacingXXSmall
import com.chocolate.presentation.theme.SpacingXLarge
import com.chocolate.presentation.theme.SpacingMedium
import com.chocolate.presentation.theme.SpacingHuge
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun StartNewMessage(
    onMessageInputChanged: (String) -> Unit,
    onSendMessage: () -> Unit,
    modifier: Modifier = Modifier,
    messageInput: String = "",
    contentDescription: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.customColors().card)
            .padding(SpacingXLarge)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpacingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TeamixTextField(
                modifier = Modifier.wrapContentHeight()
                    .fillMaxWidth(0.9f)
                    .padding(horizontal =SpacingXXSmall),
                value = messageInput,
                onValueChange = { onMessageInputChanged(it) },
                containerColor = MaterialTheme.customColors().background,
            )
            Surface(
                modifier = Modifier
                    .padding(end = SpacingXXSmall)
                    .size(SpacingHuge)
            ) {
                IconButton(
                    onClick = { onSendMessage().takeIf { messageInput.isNotEmpty() } },
                    modifier = Modifier
                        .background(MaterialTheme.customColors().card)
                        .size(SpacingHuge),
                ) {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.arrow_right
                        ),
                        tint = if (messageInput.isNotEmpty()) MaterialTheme.customColors().primary
                        else MaterialTheme.customColors().gray,
                        contentDescription = contentDescription
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
            {}, {},
        )
    }
}
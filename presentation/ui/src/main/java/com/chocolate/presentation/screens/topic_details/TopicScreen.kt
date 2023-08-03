package com.chocolate.presentation.screens.topic_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.chocolate.presentation.screens.channel.composables.CustomAppBar
import com.chocolate.presentation.screens.topic_details.composables.ReplyMessage
import com.chocolate.presentation.screens.topic_details.composables.StartNewMessage
import com.chocolate.presentation.theme.Space16
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.theme.customColors

@Composable
fun TopicScreen() {
    TopicContent(
        navigationBack = {},
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopicContent(
    topicName: String = "Topic Name",
    navigationBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.customColors().background,//todo change this color to be in dark theme
        topBar = {
            //todo this app bar must be changed to be one composable for all screens
            CustomAppBar(
                title = topicName,
                navigationBack = navigationBack,
            )
        },
        bottomBar = {
            StartNewMessage(
                modifier = Modifier
            )
        }
        ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            val (messages) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .constrainAs(messages) {
                        bottom.linkTo(parent.bottom)
                    },
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(Space16),
                contentPadding = PaddingValues(bottom = Space16 )
            ) {
                items(6) {
                    ReplyMessage(
                        isMyReplay = it%2==0
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun TopicContentScreenPreview() {
    TeamixTheme() {
        TopicScreen()
    }
}
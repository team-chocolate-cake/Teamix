package com.chocolate.presentation.screens.channel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chocolate.presentation.R
import com.chocolate.presentation.screens.channel.composables.CustomAppBar
import com.chocolate.presentation.screens.channel.composables.ReplyMessage
import com.chocolate.presentation.screens.channel.composables.StartNewMessage
import com.chocolate.presentation.screens.channel.composables.Topic
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
    topicName:String = "Topic Name",
    navigationBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,//todo change this color to be in dark theme
        topBar = {
            //todo this app bar must be changed to be one composable for all screens
            CustomAppBar(
                title = topicName,
                navigationBack = navigationBack,
            )
        },

    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            val (messages , newMessage) = createRefs()
            LazyColumn(
                reverseLayout = true,
                verticalArrangement = Arrangement.spacedBy(Space16),
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                items(6) {
                    ReplyMessage()
                }
            }
            StartNewMessage(
                modifier = Modifier.constrainAs(newMessage){
                    bottom.linkTo(parent.bottom)
                }
            )
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
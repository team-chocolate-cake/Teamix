package com.chocolate.presentation.screens.meeting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.chocolate.presentation.main.MainActivity
import com.chocolate.presentation.util.LocalNavController
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.core.call.state.FlipCamera
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.call.state.ToggleCamera
import io.getstream.video.android.core.call.state.ToggleMicrophone
import io.getstream.video.android.model.User
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MeetingScreen() {
    val navController = LocalNavController.current
    val userToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiU2hhYWtfVGkiLCJpc3MiOiJwcm9udG8iLCJzdWIiOiJ1c2VyL1NoYWFrX1RpIiwiaWF0IjoxNjk2MzEwMDIxLCJleHAiOjE2OTY5MTQ4MjZ9.r1igXzJtHcRIsC2ej3vJcI6TLwd_oB7nsIk6VMtKqys"
    val userId = "Shaak_Ti"
    val callId = "FHgloLFric09"
    val apiKey = "mmhfdzb5evj2"

    val client = StreamVideoBuilder(
        context = MainActivity().applicationContext,
        apiKey = apiKey,
        user = User(
            id = userId,
            name = "mimo",
            role = "admin",
        ),
        token = userToken,
    ).build()

    val call = client.call(type = "default", id = callId)
    MainActivity().lifecycleScope.launch {
        call.join(create = true)
    }
    VideoTheme {
        CallContent(
            modifier = Modifier.fillMaxSize(),
            call = call,
            onBackPressed = { navController.popBackStack() },
            onCallAction = { callAction ->
                when (callAction) {
                    is FlipCamera -> call.camera.flip()
                    is ToggleCamera -> call.camera.setEnabled(callAction.isEnabled)
                    is ToggleMicrophone -> call.microphone.setEnabled(callAction.isEnabled)
                    is LeaveCall -> navController.popBackStack()
                    else -> Unit
                }
            },
        )
    }
}
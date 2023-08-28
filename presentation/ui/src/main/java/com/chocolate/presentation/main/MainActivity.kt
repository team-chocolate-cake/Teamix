package com.chocolate.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.chocolate.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.video.android.compose.permission.LaunchCallPermissions
import io.getstream.video.android.compose.theme.VideoTheme
import io.getstream.video.android.compose.ui.components.call.renderer.FloatingParticipantVideo
import io.getstream.video.android.compose.ui.components.video.VideoRenderer
import io.getstream.video.android.core.GEO
import io.getstream.video.android.core.RealtimeConnection
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import kotlinx.coroutines.launch
import androidx.compose.material3.Text
import com.chocolate.presentation.TeamixApp
import com.chocolate.presentation.theme.TeamixTheme
import com.chocolate.presentation.util.InstallSavedAppLanguage


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    val user = User(
        id = "8202206",
        name = "Bilal"
    )

    val client = StreamVideoBuilder(
        context = applicationContext,
        apiKey = "hd8szvscpxvd", // demo API key
        geo = GEO.GlobalEdgeNetwork,
        user = user,
        token = "cu3gwusmy46fy4ybhs9y3vnjsmgxpnh2vzb92kuxgqwewp5hneahmnj6yxra3e8d",
    ).build()

    val call = client.call("default", "123")



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LaunchCallPermissions(call = call)

            InstallSavedAppLanguage(this)
            val isDarkTheme by mainViewModel.state.collectAsState()
            TeamixTheme(isDarkTheme) {
                TeamixApp(isDarkTheme)
            }
//
//            val participants by call.state.participants.collectAsState()
//            val connection by call.state.connection.collectAsState()
//
//            lifecycleScope.launch {
//                val result = call.join(create = true)
//                result.onError {
//                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
//                }
//            }
//
//
//            VideoTheme {
//                val remoteParticipants by call.state.remoteParticipants.collectAsState()
//                val remoteParticipant = remoteParticipants.firstOrNull()
//                val me by call.state.me.collectAsState()
//                val connection by call.state.connection.collectAsState()
//                var parentSize: IntSize by remember { mutableStateOf(IntSize(0, 0)) }
//
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(VideoTheme.colors.appBackground)
//                        .onSizeChanged { parentSize = it }
//                ) {
//                    if (remoteParticipant != null) {
//                        val remoteVideo by remoteParticipant.video.collectAsState()
//
//                        Column(modifier = Modifier.fillMaxSize()) {
//                            VideoRenderer(
//                                modifier = Modifier.weight(1f),
//                                call = call,
//                                video = remoteVideo
//                            )
//                        }
//                    } else {
//                        if (connection != RealtimeConnection.Connected) {
//                            Text(
//                                text = "loading...",
//                                fontSize = 30.sp,
//                                color = VideoTheme.colors.textHighEmphasis
//                            )
//                        } else {
//                            Text(
//                                modifier = Modifier.padding(30.dp),
//                                text = "Join call ${call.id} in your browser to see the video here",
//                                fontSize = 30.sp,
//                                color = VideoTheme.colors.textHighEmphasis,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//
//                    // floating video UI for the local video participant
//                    me?.let { localVideo ->
//                        FloatingParticipantVideo(
//                            modifier = Modifier.align(Alignment.TopEnd),
//                            call = call,
//                            participant = localVideo,
//                            parentBounds = parentSize
//                        )
//                    }
//                }
//            }
//
        }
    }
}


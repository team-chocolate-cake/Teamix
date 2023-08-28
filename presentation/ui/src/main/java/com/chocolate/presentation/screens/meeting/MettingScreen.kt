package com.chocolate.presentation.screens.meeting

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.chocolate.viewmodel.meeting.MeetingInteraction
import com.chocolate.viewmodel.meeting.MeetingUiState
import com.chocolate.viewmodel.meeting.MeetingViewModel

@Composable
fun MeetingScreen(
    meetingViewModel: MeetingViewModel = hiltViewModel()
) {
    val state by meetingViewModel.state.collectAsState()

    MeetingContent(
        state = state,
        meetingInteraction = meetingViewModel
    )
}

@Composable
private fun MeetingContent(
    state: MeetingUiState,
    meetingInteraction: MeetingInteraction
) {

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            meetingInteraction.onPermissionResult(
                acceptedAudioPermission = perms[android.Manifest.permission.RECORD_AUDIO] == true,
                acceptedCameraPermission = perms[android.Manifest.permission.CAMERA] == true,
            )
        }
    )

    LaunchedEffect(key1 = true){
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.CAMERA
            )
        )
    }


}
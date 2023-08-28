package com.chocolate.viewmodel.meeting

import com.chocolate.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.update

class MeetingViewModel : BaseViewModel<MeetingUiState, MeetingUiEffect>(MeetingUiState()),
    MeetingInteraction {

    override fun onPermissionResult(
        acceptedAudioPermission: Boolean,
        acceptedCameraPermission: Boolean,
    ) {
        _state.update {
            it.copy(
                hasAudioPermission = acceptedAudioPermission,
                hasCameraPermission = acceptedCameraPermission
            )
        }
    }
}
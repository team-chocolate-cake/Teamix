package com.chocolate.viewmodel.meeting

interface MeetingInteraction {
    fun onPermissionResult(acceptedAudioPermission: Boolean, acceptedCameraPermission: Boolean)

}
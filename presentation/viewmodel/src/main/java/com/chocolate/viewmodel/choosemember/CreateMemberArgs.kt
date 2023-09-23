package com.chocolate.viewmodel.choosemember

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

class CreateMemberArgs @Inject constructor(savedStateHandle: SavedStateHandle) {
    val role: String = savedStateHandle[ROLE] ?: "Member"

    companion object {
        const val ROLE = "role"
    }
}
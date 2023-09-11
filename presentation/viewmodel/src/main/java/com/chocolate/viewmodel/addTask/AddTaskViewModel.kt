package com.chocolate.viewmodel.addTask

import android.util.Log
import com.chocolate.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor() :
    BaseViewModel<AddTaskUiState, AddTaskUiEffect>(AddTaskUiState()), AddTaskScreenInteraction {

    init {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
        val now = Date()
        _state.update {
            it.copy(
                startDate = it.startDate.copy(
                    date = dateFormatter.format(now),
                    time = timeFormatter.format(now),
                )
            )
        }

    }


    override fun onChangeTitle(title: String) {
        _state.update { it.copy(title = title) }
    }

    override fun onCLickStartDate() {
        _state.update { it.copy(isShowDatePickerDialog = true) }

    }

    override fun onCLickConfirmDatePickerDialog(dateTime: Long?) {
        _state.update { it.copy(isShowDatePickerDialog = false) }
    }

    private fun convertDateToString(date: Long?): String {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy - ", Locale.getDefault())
        return dateFormatter.format(date)
    }

    override fun onCLickCancelDatePickerDialog() {
        _state.update { it.copy(isShowDatePickerDialog = false) }

    }

    override fun onclickAssignTask(idMember: Int) {
        Log.v("Ameerxzu", "onclickAssignTask ${idMember}")

        val membersOrganization = _state.value.membersOrganization.toMutableList()

        if (membersOrganization.isNotEmpty()) {
            membersOrganization.map { member ->
                if (member.id == idMember) {
                    member.copy(isSelected = !member.isSelected)
                } else {
                    member
                }
            }
            Log.v("Ameerxzu", "asd ${idMember}")
        }


        _state.update { it.copy(membersOrganization = membersOrganization) }

    }


}
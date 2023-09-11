package com.chocolate.viewmodel.addTask

interface AddTaskScreenInteraction {
    fun onChangeTitle(title: String)

    fun onCLickStartDate()
    fun onCLickConfirmDatePickerDialog(dateTime: Long?)
    fun onCLickCancelDatePickerDialog()
    fun onclickAssignTask(idMember: Int)
}
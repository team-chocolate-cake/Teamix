package com.chocolate.viewmodel.organizationTasks

import com.chocolate.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class OrganizationTasksViewModel @Inject constructor(

):BaseViewModel<OrganizationTasksUiState,Unit>(OrganizationTasksUiState()){

    init {
        testData()
    }
    private fun testData(){
        _state.update { it.copy(
            tasks = listOf(
                TasksUiState("clean code","fst",
                    "sec",false,
                    false,"2023-8-23",0.3f),
                TasksUiState("design pattern","fst",
                    "sec",false,
                    false,"2023-9-1",1f),
                )
        )}
    }
}
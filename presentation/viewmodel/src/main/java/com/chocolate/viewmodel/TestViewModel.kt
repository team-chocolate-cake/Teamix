package com.chocolate.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.chocolate.usecases.users.UsersUSeCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor (
   val usersUSeCase: UsersUSeCase
) : ViewModel()  {
    val TAG="TAG"


    fun test(){
        viewModelScope.launch {
            val result= usersUSeCase()
            Log.d(TAG, "Result: $result")
        }

    }


}
package com.chocolate.viewmodel.login

interface LoginInteraction {
    fun updateEmailState(email: String)
    fun updatePasswordState(password: String)
    fun login(email: String, password: String)
}
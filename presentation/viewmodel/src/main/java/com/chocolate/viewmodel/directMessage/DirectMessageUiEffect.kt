package com.chocolate.viewmodel.directMessage

sealed interface DirectMessageUiEffect{
    object NavigateToChooseMember : DirectMessageUiEffect
}
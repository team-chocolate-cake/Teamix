package com.chocolate.repository.messages

import com.chocolate.remote.messages.service.MessageService
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageService: MessageService
){

}
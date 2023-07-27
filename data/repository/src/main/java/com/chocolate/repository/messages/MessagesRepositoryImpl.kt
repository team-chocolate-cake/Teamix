package com.chocolate.repository.messages

import com.chocolate.entities.messages.NarrowItemEntity
import com.chocolate.remote.messages.service.MessageService
import repositories.messages.MessagesRepository
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(
    private val messageService: MessageService
): MessagesRepository {

}
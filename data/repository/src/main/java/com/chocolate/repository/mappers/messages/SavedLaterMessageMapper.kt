package com.chocolate.repository.mappers.messages

import com.chocolate.entities.entity.Member
import com.chocolate.entities.entity.SavedLaterMessage
import com.chocolate.repository.model.dto.message.SavedLaterMessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<SavedLaterMessageDto>>.toEntity(member: Member): Flow<List<SavedLaterMessage>> {
    return this.map { it.map { savedLaterMessageDto -> savedLaterMessageDto.toEntity(member) } }
}

fun SavedLaterMessageDto.toEntity(member: Member): SavedLaterMessage {
    return SavedLaterMessage(
        id = id!!,
        sender = member,
        messageContent = messageContent!!,
        date = date!!
    )
}

fun SavedLaterMessage.toRemote(): SavedLaterMessageDto = SavedLaterMessageDto(
    id = id,
    senderId = sender.id,
    messageContent = messageContent,
    date = date
)
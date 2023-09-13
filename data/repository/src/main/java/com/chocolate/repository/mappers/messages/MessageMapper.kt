package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.repository.datastore.realtime.model.MessageDto
import com.chocolate.repository.model.localDto.message.SavedMessageLocalDto
import java.util.Date

@JvmName("messageDtoToMessage")
fun MessageDto.toMessage() = Message(
    id = id ?: "",
    senderId = userId ?: "",
    messageContent = content ?: "",
    senderFullName = senderName ?: "",
    senderAvatarUrl = senderImage ?: "",
    timestamp = timestamp ?: Date()
)

@JvmName("messageDtoToMessage")
fun List<MessageDto>?.toMessage(): List<Message> = this?.map { it.toMessage() }.orEmpty()


@JvmName("messageToMessageDto")
fun Message.toMessageDto() = MessageDto(
    id = id,
    userId = senderId,
    content = messageContent,
    senderName = senderFullName,
    senderImage = senderAvatarUrl,
    timestamp = timestamp
)

@JvmName("messageToMessageDto")
fun List<Message>?.toMessageDto(): List<MessageDto> = this?.map { it.toMessageDto() }.orEmpty()




@JvmName("messageToLocalDto")
fun Message.toLocalDto() = SavedMessageLocalDto(
    id = id.toInt(),
    senderId = senderId,
    messageContent = messageContent,
    senderName = senderFullName,
    senderImageUrl = senderAvatarUrl,
    date = timestamp
)

@JvmName("messageToLocalDto")
fun List<Message>?.toLocalDto(): List<SavedMessageLocalDto> = this?.map { it.toLocalDto() }.orEmpty()



@JvmName("localDtoToMessage")
fun SavedMessageLocalDto.toMessage() = Message(
    id = id.toString(),
    senderId = senderId.toString(),
    messageContent = messageContent,
    senderFullName = senderName ,
    senderAvatarUrl = senderImageUrl,
    timestamp = date
)

@JvmName("localDtoToMessage")
fun List<SavedMessageLocalDto>?.toMessage(): List<Message> = this?.map { it.toMessage() }.orEmpty()


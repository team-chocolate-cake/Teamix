package com.chocolate.repository.mappers.messages

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.Message
import com.chocolate.repository.model.dto.message.MessageDto
import com.chocolate.repository.utils.getCurrentTime
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

@RequiresApi(Build.VERSION_CODES.O)
fun Message.toMessageDto() = MessageDto(
    id = id,
    userId = senderId,
    content = messageContent,
    senderName = senderFullName,
    senderImage = senderAvatarUrl,
    timestamp = getCurrentTime()
)

@RequiresApi(Build.VERSION_CODES.O)
@JvmName("MessageDto")
fun List<Message>?.toMessageDto(): List<MessageDto> = this?.map { it.toMessageDto() }.orEmpty()
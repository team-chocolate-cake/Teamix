package com.chocolate.repository.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.chocolate.entities.entity.Message
import com.chocolate.repository.model.dto.MessageDto
import com.chocolate.repository.utils.getCurrentTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

fun Flow<List<MessageDto>>.toMessage(): Flow<List<Message>> {
    return this.map { it.map { messageDto -> messageDto.toMessage() } }
}

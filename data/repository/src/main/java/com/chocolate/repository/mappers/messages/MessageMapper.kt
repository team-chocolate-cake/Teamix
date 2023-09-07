package com.chocolate.repository.mappers.messages

import com.chocolate.entities.messages.Message
import com.chocolate.entities.messages.Reaction
import com.chocolate.repository.datastore.realtime.model.MessageDto
import com.chocolate.repository.model.dto.message.response.ReactionDto
import com.chocolate.repository.model.localDto.message.SavedMessageLocalDto
import java.sql.Timestamp


//@JvmName("MessageDto")
//fun List<MessageDto>?.toEntity(): List<Message> = this?.map { it.toEntity() }.orEmpty()

@JvmName("ReactionDto")
fun List<ReactionDto>?.toEntity(): List<Reaction> =
    this?.map { Reaction(emojiCode = it.emoji_code, emojiName = it.emoji_name) }.orEmpty()

fun SavedMessageLocalDto.toEntity(): Message{
    return Message(
        id = this.id,
        senderAvatarUrl = this.senderImageUrl,
        senderId = this.senderId,
     //   senderEmail = "",
        senderFullName = this.senderName,
     //   reactions = emptyList(),
        messageContent = this.messageContent,
        streamId = 0,
      //  senderFullName = this.senderName,
     //   topic = "",
     //   timestamp = this.date

    )
}

fun Message.toLocalDto(): SavedMessageLocalDto{
    return SavedMessageLocalDto(
        id = this.id,
        senderId = this.senderId,
        senderName =" this.senderFullName",
        senderImageUrl = "this.senderAvatarUrl",
        messageContent = this.messageContent,
        date =Timestamp.valueOf("sds")

    )

}

fun MessageDto.toMessage()=Message(
    id = id?.toInt()?:0,
    senderId=userId?:0,
    streamId=channelId?:0,
    messageContent = text?: "",
    senderFullName = senderName?:"",
    senderAvatarUrl = senderImage?:""
)

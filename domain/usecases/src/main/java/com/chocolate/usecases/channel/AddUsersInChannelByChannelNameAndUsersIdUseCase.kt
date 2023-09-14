package com.chocolate.usecases.channel

import com.chocolate.entities.exceptions.ValidationException
import com.chocolate.entities.uills.Empty
import kotlinx.coroutines.flow.firstOrNull
import repositories.ChannelsRepository
import javax.inject.Inject

class AddUsersInChannelByChannelNameAndUsersIdUseCase @Inject constructor(
    private val repository: ChannelsRepository
) {

    suspend operator fun invoke(
        channelName: String,
        usersId: List<String> = listOf(),
        description: String? = "",
        isPrivate: Boolean = false
    ): Boolean {
        if (isValidChannelName(channelName)) {
            createChannel(
                channelName = channelName,
                usersId = usersId,
                description = description,
                isPrivate = isPrivate
            )
            return true
        } else {
            throw ValidationException(String.Empty)
        }
    }

    private suspend fun isValidChannelName(channelName: String): Boolean {
        return channelName.isNotBlank() && channelName.length < 60 && !isChannelAlreadyExists(
            channelName
        )
    }

    private suspend fun createChannel(
        channelName: String,
        usersId: List<String>,
        description: String?,
        isPrivate: Boolean
    ): Boolean {

        return repository.subscribeToChannel(
            channelName = channelName,
            usersId = usersId,
            description = description,
            isPrivate = isPrivate
        )
    }

    private suspend fun isChannelAlreadyExists(channelName: String): Boolean {
        return repository.getStreamChannels().firstOrNull()?.any { it.name == channelName } == true
    }


}
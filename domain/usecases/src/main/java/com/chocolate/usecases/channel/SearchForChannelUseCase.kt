package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.BlankSearchQueryException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import repositories.ChannelsRepository
import javax.inject.Inject

class SearchForChannelUseCase @Inject constructor(
    private val channelsRepository: ChannelsRepository
) {

    suspend operator fun invoke(searchQuery: String): Flow<List<Channel>> {
        return searchQuery.takeIf { it.isNotBlank() || it.isNotEmpty() }?.run {
            channelsRepository.getChannelsInCurrentOrganization()
                .map { channels ->
                    channels.filter { it.name.contains(searchQuery, true) }
                }
        } ?: throw BlankSearchQueryException
    }

}


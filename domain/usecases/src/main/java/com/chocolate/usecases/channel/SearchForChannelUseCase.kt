package com.chocolate.usecases.channel

import com.chocolate.entities.channel.Channel
import com.chocolate.entities.exceptions.BlankSearchQueryException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchForChannelUseCase @Inject constructor(
    private val getChannelsForCurrentMemberUseCase: GetChannelsForCurrentMemberUseCase,
) {
    suspend operator fun invoke(searchQuery: String): Flow<List<Channel>> {
        return searchQuery.takeIf { it.isNotBlank() || it.isNotEmpty() }?.run {
            getChannelsForCurrentMemberUseCase().map { channels ->
                channels.filter { it.name.contains(searchQuery, true) }
            }
        } ?: throw BlankSearchQueryException
    }
}


package com.chocolate.usecases.search

import com.chocolate.entities.Channel
import com.chocolate.entities.utils.BlankSearchQueryException
import com.chocolate.usecases.channel.ManageChannelUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchForChannelUseCase @Inject constructor(
    private val manageChannelUseCase: ManageChannelUseCase,
) {
    suspend operator fun invoke(searchQuery: String): Flow<List<Channel>> {
        return searchQuery.takeIf { it.isNotBlank() || it.isNotEmpty() }?.run {
            manageChannelUseCase.getChannelsForCurrentMember().map { channels ->
                channels.filter { it.name.contains(searchQuery, true) }
            }
        } ?: throw BlankSearchQueryException
    }
}


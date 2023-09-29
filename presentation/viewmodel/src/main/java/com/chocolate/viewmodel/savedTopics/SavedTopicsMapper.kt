package com.chocolate.viewmodel.savedTopics

import com.chocolate.entities.entity.Topic
import com.chocolate.entities.util.toStringDate


fun Topic.toSavedTopicsItemUiState(): SavedTopicsItemUiState = SavedTopicsItemUiState(
    id = topicId,
    creatorName = name,
    creatorImage = senderImage,
    topicContent = name,
    sentTime = sentTime.toStringDate()
)

fun List<Topic>.toSavedTopicsItemsUiState(): List<SavedTopicsItemUiState> {
    return this.map { it.toSavedTopicsItemUiState() }
}

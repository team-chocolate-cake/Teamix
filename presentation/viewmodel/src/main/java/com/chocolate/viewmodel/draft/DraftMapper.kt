package com.chocolate.viewmodel.draft

import com.chocolate.entities.entity.Draft


@JvmName("toDraftItemUiState")
fun Draft.toUiState(): DraftItemUiState {
   return DraftItemUiState(
       id = id,
       messageContent = content,
       time = timestamp.toString(),
       topicName = topic,
       isInStream = isInStream,
   )

}

@JvmName("toDraftUiState")
fun List<Draft>.toUiState(): List<DraftItemUiState> = this.map {it.toUiState()}

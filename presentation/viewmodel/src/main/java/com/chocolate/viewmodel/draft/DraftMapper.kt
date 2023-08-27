package com.chocolate.viewmodel.draft

import com.chocolate.entities.draft.Draft


@JvmName("toDraftItemUiState")
fun Draft.toUiState(): DraftItemUiState {
   return DraftItemUiState(
        id = id,
        username = "",
        imageUrl = "",
        messageContent = content,
        time = timestamp.toString()

    )

}

@JvmName("toDraftUiState")
fun List<Draft>.toUiState(): List<DraftItemUiState> = this.map {it.toUiState()}

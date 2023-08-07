package com.chocolate.local.dao.draft

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.dto.local.draft.DraftLocalDto

@Dao
interface DraftMessagesDao {

    @Upsert
    suspend fun upsertDraft(draft: DraftLocalDto)

    @Query("SELECT * FROM DRAFT_TABLE WHERE id = :id")
    suspend fun getDraftById(id: String): DraftLocalDto?

    @Query("DELETE FROM DRAFT_TABLE")
    suspend fun deleteDraftMessage(draftId: Int)

}
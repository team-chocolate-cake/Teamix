package com.chocolate.local.dao.draft

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.draft.DraftLocalDto

@Dao
interface DraftMessagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraft(draft: DraftLocalDto)


    @Query("SELECT * FROM Draft_table WHERE id = :id")
    suspend fun getDraftById(id: String): DraftLocalDto?


    @Delete
    suspend fun deleteSavedDraft(draft: DraftLocalDto)

}
package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.draft.DraftEntity

@Dao
interface DraftMessagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraft(draft: DraftEntity)


    @Query("SELECT * FROM Draft_table WHERE id = :id")
    suspend fun getDraftById(id: String): DraftEntity?


    @Delete
    suspend fun deleteSavedDraft(draft: DraftEntity)

}
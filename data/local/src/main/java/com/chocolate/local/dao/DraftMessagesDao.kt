package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.draft.DraftEntity

@Dao
interface DraftMessagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDraft(draft: DraftEntity)


    @Query("SELECT * FROM Draft_table WHERE id = :id")
    fun getDraftById(id: String): DraftEntity?


    @Delete
    fun deleteSavedDraft(draft: DraftEntity)

}
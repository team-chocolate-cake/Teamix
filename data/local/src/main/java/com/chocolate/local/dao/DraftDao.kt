package com.chocolate.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.draft.DraftEntity

interface DraftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDraft(draft: DraftEntity)


    @Query("SELECT * FROM Draft_table WHERE id = :id")
    fun getDraftById(id: String): DraftEntity?


    @Delete
    fun deleteSavedDraft(draft: DraftEntity)

}
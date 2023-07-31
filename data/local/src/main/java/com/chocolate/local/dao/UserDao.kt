package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.users.StoriesEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStory(story: StoriesEntity)


    @Query("SELECT * FROM story_table WHERE id = :id")
    fun getStoryById(id: String): StoriesEntity?


    @Delete
    fun deleteSavedStory(story: StoriesEntity)

}
package com.chocolate.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.local.entities.trends.TrendsEntity
import com.chocolate.local.entities.users.StoriesEntity

@Dao
interface TrendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrend(trend: TrendsEntity)


    @Query("SELECT * FROM trend_table WHERE id = :id")
    fun getTrendById(id: String): TrendsEntity?



    @Delete
    fun deleteSavedTrend(trend: TrendsEntity)

}
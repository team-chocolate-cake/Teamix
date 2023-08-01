package com.chocolate.local.dao.trend

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.trends.TrendsLocalDto

@Dao
interface TrendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrend(trend: TrendsLocalDto)

    @Query("SELECT * FROM trend_table WHERE id = :id")
    suspend fun getTrendById(id: String): TrendsLocalDto?

    @Delete
    suspend fun deleteSavedTrend(trend: TrendsLocalDto)

}
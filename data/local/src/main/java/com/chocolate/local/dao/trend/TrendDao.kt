package com.chocolate.local.dao.trend

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.chocolate.repository.dto.local.trends.TrendsLocalDto

@Dao
interface TrendDao {

    @Upsert
    suspend fun upsertTrend(trend: TrendsLocalDto)

    @Query("SELECT * FROM trend_table WHERE id = :id")
    suspend fun getTrendById(id: String): TrendsLocalDto?

    @Query("DELETE FROM trend_table WHERE id = :trendId")
    suspend fun deleteSavedTrend(trendId: Int)

}
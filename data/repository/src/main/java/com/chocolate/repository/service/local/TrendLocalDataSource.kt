package com.chocolate.repository.service.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolate.repository.dto.local.trends.TrendsLocalDto

interface TrendLocalDataSource {

    suspend fun insertTrend(trend: TrendsLocalDto)

    suspend fun getTrendById(id: String): TrendsLocalDto?

    suspend fun deleteSavedTrend(trend: TrendsLocalDto)
}
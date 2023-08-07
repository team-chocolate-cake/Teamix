package com.chocolate.repository.service.local

import com.chocolate.repository.dto.local.trends.TrendsLocalDto

interface TrendLocalDataSource {

    suspend fun insertTrend(trend: TrendsLocalDto)

    suspend fun getTrendById(id: String): TrendsLocalDto?

    suspend fun deleteSavedTrend(trendId: Int)
}
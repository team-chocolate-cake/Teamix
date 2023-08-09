package com.chocolate.repository.service.local

import com.chocolate.repository.model.localDto.trends.TrendsLocalDto

interface TrendRoomDataSource {

    suspend fun insertTrend(trend: TrendsLocalDto)

    suspend fun getTrendById(id: String): TrendsLocalDto?

    suspend fun deleteSavedTrend(trendId: Int)
}
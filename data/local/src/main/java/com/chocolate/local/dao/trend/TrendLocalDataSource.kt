package com.chocolate.local.dao.trend

import com.chocolate.repository.model.localDto.trends.TrendsLocalDto
import com.chocolate.repository.service.local.TrendRoomDataSource
import javax.inject.Inject

class TrendLocalDataSource @Inject constructor(
    private val trendDao: TrendDao
) : TrendRoomDataSource {
    override suspend fun insertTrend(trend: TrendsLocalDto) {
        trendDao.upsertTrend(trend)
    }

    override suspend fun getTrendById(id: String): TrendsLocalDto? {
        return trendDao.getTrendById(id)
    }

    override suspend fun deleteSavedTrend(trendId: Int) {
        trendDao.deleteSavedTrend(trendId)
    }

}
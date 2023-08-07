package com.chocolate.local.dao.trend

import com.chocolate.repository.dto.local.trends.TrendsLocalDto
import com.chocolate.repository.service.local.TrendLocalDataSource
import javax.inject.Inject

class TrendLocalDataSourceImpl @Inject constructor(
    private val trendDao: TrendDao
) : TrendLocalDataSource {
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
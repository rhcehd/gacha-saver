package dev.rhcehd123.core.data.repository

import dev.rhcehd123.core.model.GachaItem
import dev.rhcehd123.core.network.GachaSaverNetworkDataSource
import javax.inject.Inject

class GachaSaverRepositoryImpl @Inject constructor(
    private val gachaSaverNetworkDataSource: GachaSaverNetworkDataSource
): GachaSaverRepository {
    override suspend fun requestGacha(id: String, count: Int): List<GachaItem>
            = gachaSaverNetworkDataSource.requestGacha(id, count)
}
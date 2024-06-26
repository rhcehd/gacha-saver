package dev.rhcehd123.samplegame.core.data.repository.impl

import dev.rhcehd123.samplegame.core.data.repository.GachaSaverRepository
import dev.rhcehd123.samplegame.core.model.GachaItem
import dev.rhcehd123.samplegame.core.network.GachaSaverNetworkDataSource
import javax.inject.Inject

class GachaSaverRepositoryImpl @Inject constructor(
    private val gachaSaverNetworkDataSource: GachaSaverNetworkDataSource
): GachaSaverRepository {
    override suspend fun requestGacha(id: String, count: Int): List<GachaItem>
            = gachaSaverNetworkDataSource.requestGacha(id, count)
}
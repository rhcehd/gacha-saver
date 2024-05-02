package dev.rhcehd123.core.network

import dev.rhcehd123.core.model.GachaItem
import dev.rhcehd123.core.network.model.GachaResult

interface GachaSaverNetworkDataSource {
    suspend fun requestGacha(id: String, count: Int): List<GachaItem>
}
package dev.rhcehd123.samplegame.core.network

import dev.rhcehd123.samplegame.core.model.GachaItem

interface GachaSaverNetworkDataSource {
    suspend fun requestGacha(id: String, count: Int): List<GachaItem>
}
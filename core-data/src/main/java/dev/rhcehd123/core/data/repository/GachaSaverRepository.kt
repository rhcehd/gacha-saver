package dev.rhcehd123.core.data.repository

import dev.rhcehd123.core.model.GachaItem
import dev.rhcehd123.core.network.model.GachaResult

interface GachaSaverRepository {
    suspend fun requestGacha(id: String, count: Int): List<GachaItem>
}
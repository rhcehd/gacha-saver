package dev.rhcehd123.samplegame.core.data.repository

import dev.rhcehd123.samplegame.core.model.GachaItem

interface GachaSaverRepository {
    suspend fun requestGacha(id: String, count: Int): List<GachaItem>
}
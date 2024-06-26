package dev.rhcehd123.samplegame.core.data.repository

import dev.rhcehd123.samplegame.core.model.GachaData
import kotlinx.coroutines.flow.Flow

interface GachaDataRepository {
    val gachaData: Flow<GachaData>
    suspend fun updateGachaData(gachaData: GachaData)
    suspend fun resetData()
}
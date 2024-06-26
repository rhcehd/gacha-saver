package dev.rhcehd123.samplegame.core.data.repository

import dev.rhcehd123.core.datastore.GachaDataSource
import dev.rhcehd123.samplegame.core.model.GachaData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GachaDataRepositoryImpl @Inject constructor(
    private val gachaDataSource: GachaDataSource
): GachaDataRepository {
    override val gachaData: Flow<GachaData>
        = gachaDataSource.gachaData

    override suspend fun updateGachaData(gachaData: GachaData) {
        gachaDataSource.changeGachaData(gachaData)
    }

    override suspend fun resetData() {
        gachaDataSource.setDefaultData()
    }

}
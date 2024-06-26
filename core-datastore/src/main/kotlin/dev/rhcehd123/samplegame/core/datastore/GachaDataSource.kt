package dev.rhcehd123.samplegame.core.datastore

import androidx.datastore.core.DataStore
import dev.rhcehd123.samplegame.core.model.GachaData
import dev.rhcehd123.samplegame.core.model.GachaData.Companion.defaultGachaData
import dev.rhcehd123.samplegame.core.model.GachaGroup
import dev.rhcehd123.samplegame.core.model.GachaItem
import dev.rhcehd123.samplegame.core.datastore.data.GachaTable
import dev.rhcehd123.samplegame.core.datastore.data.Group
import dev.rhcehd123.samplegame.core.datastore.data.Item
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GachaDataSource @Inject constructor(
    private val gachaTable: DataStore<GachaTable>
) {
    val gachaData = gachaTable.data.map {
        if(it.isEmpty()) {
            defaultGachaData
        } else {
            GachaData(
                name = it.name,
                groupList = it.gachaGroupList.map { group ->
                    GachaGroup(
                        name = group.name,
                        groupRate = group.groupRate,
                        itemList = group.gachaItemList.map { item ->
                            GachaItem(
                                name = item.name,
                                groupName = item.groupName,
                                rate = item.itemRate
                            )
                        }
                    )
                }
            )
        }
    }

    private fun GachaTable.isEmpty(): Boolean = name.isEmpty() && gachaGroupList.isEmpty()

    suspend fun clearData() {
        gachaTable.updateData {
            it.toBuilder()
                .clear()
                .build()
        }
    }

    suspend fun setDefaultData() {
        gachaTable.updateData {
            it.toBuilder()
                .clear()
                .setName(defaultGachaData.name)
                .addAllGachaGroup(defaultGachaData.groupList.map { group ->
                    Group.newBuilder()
                        .setName(group.name)
                        .setGroupRate(group.groupRate)
                        .addAllGachaItem(group.itemList.map { item ->
                            Item.newBuilder()
                                .setName(item.name)
                                .setGroupName(item.groupName)
                                .setItemRate(item.rate)
                                .build()
                        })
                        .build()
                })
                .build()
        }
    }

    suspend fun changeGachaData(gachaData: GachaData) {
        gachaTable.updateData {
            it.toBuilder()
                .clear()
                .setName(gachaData.name)
                .addAllGachaGroup(gachaData.groupList.map { group ->
                    Group.newBuilder()
                        .setName(group.name)
                        .setGroupRate(group.groupRate)
                        .addAllGachaItem(group.itemList.map { item ->
                            Item.newBuilder()
                                .setName(item.name)
                                .setItemRate(item.rate)
                                .build()
                        })
                        .build()
                })
                .build()
        }
    }
}
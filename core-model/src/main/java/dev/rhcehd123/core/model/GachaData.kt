package dev.rhcehd123.core.model

data class GachaData(
    val name: String,
    val groupList: List<GachaGroup>,
    //val itemList: List<GachaItem>,
) {
    companion object {
        val emptyGachaData = GachaData("", listOf())
        val defaultGachaData = GachaData(
            name = "Default Gacha",
            groupList = listOf(
                GachaGroup(
                    name = "A",
                    groupRate = "3.000",
                    itemList = listOf(
                        GachaItem(
                            name = "A-1",
                            groupName = "A",
                            rate = "1.500",
                        ),
                        GachaItem(
                            name = "A-2",
                            groupName = "A",
                            rate = "1.500",
                        )
                    )
                ),
                GachaGroup(
                    name = "B",
                    groupRate = "10.000",
                    itemList = listOf(
                        GachaItem(
                            name = "B-1",
                            groupName = "B",
                            rate = "5.000",
                        ),
                        GachaItem(
                            name = "B-2",
                            groupName = "B",
                            rate = "5.000",
                        ),
                    )
                ),
                GachaGroup(
                    name = "C",
                    groupRate = "87.000",
                    itemList = listOf(
                        GachaItem(
                            name = "C-1",
                            groupName = "C",
                            rate = "43.500",
                        ),
                        GachaItem(
                            name = "C-2",
                            groupName = "C",
                            rate = "43.500",
                        ),
                    )
                )
            )
        )
    }
}

data class GachaGroup(
    val name: String,
    val groupRate: String,
    val itemList: List<GachaItem>
)

data class GachaItem(
    val name: String,
    val groupName: String,
    val rate: String,
)

fun GachaData.updateGroup(groups: List<GachaGroup>): GachaData {
    val newList = groupList.map { gachaGroup ->
        groups.find { it.name == gachaGroup.name } ?: gachaGroup
    }
    return copy(groupList = newList)
}

fun GachaGroup.updateItem(items: List<GachaItem>): GachaGroup {
    val newList = itemList.map { gachaItem ->
        items.find { it.name == gachaItem.name } ?: gachaItem
    }
    return copy(itemList = newList)
}
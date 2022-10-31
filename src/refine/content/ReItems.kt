package refine.content

import arc.graphics.Color
import mindustry.Vars
import mindustry.ctype.ContentType
import mindustry.type.Item

class ReItems {

    companion object {
        lateinit var rawCopper: Item
        lateinit var rawLead: Item
        lateinit var rawTitanium: Item
        lateinit var basalt: Item
        lateinit var sulfur: Item

        fun load() {
            rawCopper = Item("raw-copper", Color.valueOf("d99d73")).apply {
                hardness = 1
                alwaysUnlocked = true
            }
            rawLead = Item("raw-lead", Color.valueOf("8c7fa9")).apply {
                hardness = 1
                alwaysUnlocked = true
            }
            rawTitanium = Item("raw-titanium", Color.valueOf("8da1e3")).apply {
                hardness = 3
            }
            basalt = Item("basalt", Color.valueOf("5e6364")).apply {
                lowPriority = true
            }
            sulfur = Item("sulfur", Color.valueOf("f1dd38"))
        }

        fun getCorrespondingItem(item: Item): Item {
            return Vars.content.getByName(ContentType.item, item.name.removePrefix("refine-raw-"))
        }
    }
}
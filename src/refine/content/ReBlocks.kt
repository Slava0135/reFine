package refine.content

import mindustry.ctype.ContentList
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.Block
import mindustry.world.blocks.environment.OreBlock
import refine.world.blocks.production.Furnace

class ReBlocks : ContentList {

    override fun load() {
        oreGranite = object : OreBlock(ReItems.granite) {
            init {
                oreDefault = true
                oreThreshold = 0.81f
                oreScale = 23.47619f
            }
        }
        furnace = object : Furnace("furnace") {
            init {
                requirements(Category.production, ItemStack.with(ReItems.granite, 12))
                size = 2
            }
        }
    }

    companion object {
        var furnace: Block? = null
        var oreGranite: Block? = null
    }
}
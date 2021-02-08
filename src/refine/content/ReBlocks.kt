package refine.content

import mindustry.content.Items
import mindustry.ctype.ContentList
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.world.Block
import mindustry.world.blocks.environment.OreBlock
import refine.world.blocks.production.ElectricFurnace
import refine.world.blocks.production.Furnace

class ReBlocks : ContentList {

    override fun load() {
        oreGranite = object : OreBlock(ReItems.granite) {
            init {
                oreDefault = true
                oreThreshold = 0.88f
                oreScale = 24.5f
            }
        }
        furnace = object : Furnace("furnace") {
            init {
                requirements(Category.production, ItemStack.with(ReItems.granite, 12))
                size = 2
            }
        }
        electricFurnace = object : ElectricFurnace("electric-furnace") {
            init {
                requirements(Category.production, ItemStack.with(ReItems.granite, 35, Items.graphite, 35, Items.titanium, 35))
                consumes.power(1f)
                size = 3
            }
        }
    }

    companion object {
        var furnace: Block? = null
        var oreGranite: Block? = null
        var electricFurnace: Block? = null
    }
}
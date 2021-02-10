package refine.content

import mindustry.content.Fx
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.ctype.ContentList
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.Block
import mindustry.world.blocks.environment.OreBlock
import mindustry.world.blocks.production.GenericCrafter
import mindustry.world.blocks.production.LiquidConverter
import mindustry.world.draw.DrawMixer
import refine.world.blocks.production.ElectricFurnace
import refine.world.blocks.production.Furnace

class ReBlocks : ContentList {

    override fun load() {
        oreBasalt = object : OreBlock(ReItems.basalt) {
            init {
                oreDefault = true
                oreThreshold = 0.88f
                oreScale = 24.5f
            }
        }
        furnace = object : Furnace("furnace") {
            init {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 12))
                size = 2
                alwaysUnlocked = true
            }
        }
        electricFurnace = object : ElectricFurnace("electric-furnace") {
            init {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 35, Items.graphite, 35, Items.titanium, 35))
                consumes.power(1f)
                size = 3
                alwaysUnlocked = true
            }
        }
        sulfurCentrifuge = object : GenericCrafter("sulfur-centrifuge") {
            init {
                requirements(Category.crafting, ItemStack.with(Items.titanium, 50, Items.graphite, 40, Items.lead, 30))
                craftEffect = Fx.smeltsmoke
                outputItem = ItemStack(ReItems.sulfur, 1)
                craftTime = 30f
                size = 2
                hasPower = true.also { hasLiquids = it }.also { hasItems = it }

                consumes.liquid(Liquids.oil, 0.1f)
                consumes.power(1f)
            }
        }
        acidMixer = object : LiquidConverter("acid-mixer") {
            init {
                requirements(Category.crafting, ItemStack.with(Items.lead, 65, Items.silicon, 40, Items.titanium, 60))
                outputLiquid = LiquidStack(ReLiquids.acid, 0.1f)
                craftTime = 60f
                size = 2
                hasPower = true
                hasItems = true
                hasLiquids = true
                rotate = false
                solid = true
                outputsLiquid = true
                drawer = DrawMixer()
                consumes.power(1f)
                consumes.item(ReItems.sulfur)
                consumes.liquid(Liquids.water, 0.1f)
            }
        }
    }

    companion object {
        var furnace: Block? = null
        var oreBasalt: Block? = null
        var electricFurnace: Block? = null
        var sulfurCentrifuge: Block? = null
        var acidMixer: Block? = null
    }
}
package refine.content

import mindustry.content.Fx
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.Block
import mindustry.world.blocks.environment.OreBlock
import mindustry.world.blocks.production.GenericCrafter
import refine.world.blocks.production.ElectricFurnace
import refine.world.blocks.production.Furnace

class ReBlocks {

    companion object {
        lateinit var furnace: Block
        lateinit var oreBasalt: Block
        lateinit var electricFurnace: Block
        lateinit var sulfurCentrifuge: Block
        lateinit var acidMixer: Block

        fun load() {

            oreBasalt = object : OreBlock(ReItems.basalt) {}.apply {
                oreDefault = true
                oreThreshold = 0.88f
                oreScale = 24.5f
            }

            furnace = object : Furnace("furnace") {}.apply {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 12))
                size = 2
                alwaysUnlocked = true
            }

            electricFurnace = object : ElectricFurnace("electric-furnace") {}.apply {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 35, Items.graphite, 35, Items.titanium, 35))
                consumePower(1f)
                size = 3
                alwaysUnlocked = true
            }

            sulfurCentrifuge = object : GenericCrafter("sulfur-centrifuge") {}.apply {
                requirements(Category.crafting, ItemStack.with(Items.titanium, 50, Items.graphite, 40, Items.lead, 30))
                craftEffect = Fx.smeltsmoke
                outputItem = ItemStack(ReItems.sulfur, 1)
                craftTime = 30f
                size = 2
                hasPower = true.also { hasLiquids = it }.also { hasItems = it }

                consumeLiquid(Liquids.oil, 0.1f)
                consumePower(1f)

                alwaysUnlocked = true
            }

            acidMixer = object : GenericCrafter("acid-mixer") {}.apply {
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

                consumePower(1f)
                consumeItem(ReItems.sulfur)
                consumeLiquid(Liquids.water, 0.1f)

                alwaysUnlocked = true
            }
        }
    }
}
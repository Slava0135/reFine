package refine.content

import mindustry.content.Blocks
import mindustry.content.Fx
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.gen.Sounds
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.Block
import mindustry.world.blocks.defense.Wall
import mindustry.world.blocks.environment.OreBlock
import mindustry.world.blocks.production.GenericCrafter
import mindustry.world.meta.Env
import refine.world.blocks.production.AcidMixer
import refine.world.blocks.production.ElectricFurnace
import refine.world.blocks.production.Furnace

class ReBlocks {

    companion object {
        lateinit var furnace: Block
        lateinit var oreBasalt: Block
        lateinit var electricFurnace: Block
        lateinit var sulfurCentrifuge: Block
        lateinit var acidMixer: Block
        lateinit var basaltWall: Block
        lateinit var basaltWallLarge: Block

        fun load() {

            oreBasalt = OreBlock(ReItems.basalt).apply {
                oreDefault = true
                oreThreshold = 0.88f
                oreScale = 24.5f
            }

            furnace = Furnace("furnace").apply {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 12))
                size = 2
                alwaysUnlocked = true
            }

            electricFurnace = ElectricFurnace("electric-furnace").apply {
                requirements(Category.production, ItemStack.with(ReItems.basalt, 35, Items.graphite, 35, Items.titanium, 35))
                consumePower(1f)
                consumeLiquid(ReLiquids.acid, acidPerOre / smeltTime).boost().update = false
                size = 3
                alwaysUnlocked = true
            }

            sulfurCentrifuge = GenericCrafter("sulfur-centrifuge").apply {
                requirements(Category.crafting, ItemStack.with(Items.titanium, 50, Items.graphite, 40, Items.lead, 30))
                craftEffect = Fx.coalSmeltsmoke
                outputItem = ItemStack(ReItems.sulfur, 1)
                craftTime = 30f
                size = 2

                hasPower = true
                hasLiquids = true
                hasItems = true

                consumeLiquid(Liquids.oil, 0.1f)
                consumePower(1f)

                alwaysUnlocked = true
            }

            acidMixer = AcidMixer("acid-mixer").apply {
                requirements(Category.crafting, ItemStack.with(Items.lead, 65, Items.silicon, 40, Items.titanium, 60))
                outputLiquid = LiquidStack(ReLiquids.acid, 0.1f)
                craftTime = 60f
                size = 2

                craftEffect = Fx.steam
                ambientSound = Sounds.release

                hasPower = true
                hasItems = true
                hasLiquids = true

                outputsLiquid = true

                consumePower(1f)
                consumeItem(ReItems.sulfur)
                consumeLiquid(Liquids.water, 0.1f)

                alwaysUnlocked = true
            }

            basaltWall = Wall("basalt-wall").apply {
                requirements(Category.defense, ItemStack.with(ReItems.basalt, 6))
                health = 150

                alwaysUnlocked = true
            }

            basaltWallLarge = Wall("basalt-wall-large").apply {
                requirements(Category.defense, ItemStack.with(ReItems.basalt, 24))
                health = 600
                size = 2

                alwaysUnlocked = true
            }
        }
    }
}
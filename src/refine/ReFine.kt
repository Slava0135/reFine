package refine

import mindustry.Vars
import mindustry.content.Blocks
import mindustry.content.Items
import mindustry.mod.Mod
import mindustry.type.ItemStack
import mindustry.world.blocks.defense.turrets.LiquidTurret
import mindustry.world.blocks.environment.Floor
import mindustry.world.blocks.environment.OreBlock
import refine.content.*

class ReFine : Mod() {

    init {

    }
    override fun loadContent() {
        ReFX.load()
        ReStatusEffects.load()
        ReLiquids.load()
        ReItems.load()
        ReBlocks.load()
        ReBullets.load()

        Items.coal.hardness = 1

        (Blocks.oreCopper as OreBlock).itemDrop = ReItems.rawCopper
        (Blocks.oreLead as OreBlock).itemDrop = ReItems.rawLead
        (Blocks.oreTitanium as OreBlock).itemDrop = ReItems.rawTitanium

        (Blocks.basalt as Floor).itemDrop = ReItems.basalt
        (Blocks.hotrock as Floor).itemDrop = ReItems.basalt
        (Blocks.magmarock as Floor).itemDrop = ReItems.basalt

        (Blocks.tsunami as LiquidTurret).ammoTypes.put(ReLiquids.acid, ReBullets.acidTsunami)
        (Blocks.wave as LiquidTurret).ammoTypes.put(ReLiquids.acid, ReBullets.acidWave)

        Blocks.batteryLarge.requirements = Blocks.batteryLarge.requirements.plus(ItemStack(ReItems.sulfur, 100))

        Blocks.pyratiteMixer.consumeItems(*ItemStack.with(Items.coal, 1, ReItems.sulfur, 2, Items.sand, 2))
    }
}
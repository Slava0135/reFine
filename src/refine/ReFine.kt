package refine

import mindustry.Vars
import mindustry.content.Blocks
import mindustry.content.Items
import mindustry.mod.Mod
import mindustry.world.blocks.defense.turrets.LiquidTurret
import mindustry.world.blocks.environment.OreBlock
import refine.content.*

class ReFine : Mod() {
    override fun loadContent() {
        ReItems().load()
        ReBlocks().load()
        ReLiquids().load()
        ReFX().load()
        ReStatusEffects().load()
        ReBullets().load()

        Items.coal.hardness = 1

        (Vars.content.block("ore-copper") as OreBlock).itemDrop = ReItems.rawCopper
        (Vars.content.block("ore-lead") as OreBlock).itemDrop = ReItems.rawLead
        (Vars.content.block("ore-titanium") as OreBlock).itemDrop = ReItems.rawTitanium

        (Blocks.tsunami as LiquidTurret).ammoTypes.put(ReLiquids.acid, ReBullets.acidTsunami)
        (Blocks.wave as LiquidTurret).ammoTypes.put(ReLiquids.acid, ReBullets.acidWave)
    }
}
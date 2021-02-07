import content.ReItems
import mindustry.Vars
import mindustry.content.Items
import mindustry.mod.Mod
import mindustry.world.blocks.environment.OreBlock

class reFine : Mod() {

    init {

        Items.coal.hardness = 1

        val copper = Vars.content.block("ore-copper") as OreBlock
        copper.itemDrop = ReItems.rawCopper

        val lead = Vars.content.block("ore-lead") as OreBlock
        lead.itemDrop = ReItems.rawLead

        val titanium = Vars.content.block("ore-titanium") as OreBlock
        titanium.itemDrop = ReItems.rawTitanium
    }

}
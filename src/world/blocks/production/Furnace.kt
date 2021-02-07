package world.blocks.production

import mindustry.gen.Sounds
import mindustry.world.Block

open class Furnace(name: String) : Block(name) {

    override fun init() {
        super.init()
        update = true
        solid = true
        hasItems = true
        sync = true
        ambientSound = Sounds.smelter
        ambientSoundVolume = 0.07f
    }

}
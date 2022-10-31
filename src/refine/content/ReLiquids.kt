package refine.content

import arc.graphics.Color
import mindustry.type.Liquid

class ReLiquids {

    companion object {
        lateinit var acid: Liquid

        fun load() {
            acid = Liquid("acid").apply {
                temperature = 0.5f
                viscosity = 0.5f
                heatCapacity = 0.4f
                effect = ReStatusEffects.dissolving
                color = Color.valueOf("f1dd38")
            }
        }
    }
}
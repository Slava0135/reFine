package refine.content

import arc.graphics.Color
import mindustry.ctype.ContentList
import mindustry.type.Liquid

class ReLiquids : ContentList {
    override fun load() {
        acid = object : Liquid("acid") {
            init {
                temperature = 0.5f
                viscosity = 0.5f
                heatCapacity = 0.4f
                effect
                color = Color.valueOf("f1dd38")
            }
        }
    }

    companion object {
        var acid: Liquid? = null
    }
}
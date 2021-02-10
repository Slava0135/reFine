package refine.content

import arc.graphics.Color
import mindustry.ctype.ContentList
import mindustry.type.StatusEffect

class ReStatusEffects : ContentList {
    override fun load() {
        melting = object : StatusEffect("melting") {
            init {
                color = Color.valueOf("f1dd38")
                speedMultiplier = 0.8f
                healthMultiplier = 0.8f
                damage = 1f
                effect = ReFX.melting
            }
        }
    }

    companion object {
        var melting: StatusEffect? = null
    }
}
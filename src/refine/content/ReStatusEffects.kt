package refine.content

import arc.graphics.Color
import arc.math.Mathf
import mindustry.content.Fx
import mindustry.content.StatusEffects
import mindustry.entities.units.StatusEntry
import mindustry.gen.Unit
import mindustry.type.StatusEffect

class ReStatusEffects {

    companion object {
        lateinit var melting: StatusEffect
    }

    fun load() {
        melting = object : StatusEffect("acid-melting") {}.apply {
            color = Color.valueOf("ffa166")
            speedMultiplier = 0.8f
            healthMultiplier = 0.8f
            damage = 1f
            effect = ReFX.melting
        }
    }
}
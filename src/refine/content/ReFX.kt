package refine.content

import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Fill
import arc.math.Angles
import arc.math.Mathf
import mindustry.ctype.ContentList
import mindustry.entities.Effect
import mindustry.entities.Effect.EffectContainer

class ReFX : ContentList {
    override fun load() {
        melting = Effect(40f) { e: EffectContainer ->
            Draw.color(
                ReLiquids.acid!!.color,
                Color.white,
                e.fout() / 5f + Mathf.randomSeedRange(e.id.toLong(), 0.12f)
            )
            Angles.randLenVectors(
                e.id.toLong(), 2, 1f + e.fin() * 3f
            ) { x: Float, y: Float -> Fill.circle(e.x + x, e.y + y, .2f + e.fout() * 1.2f) }
        }
    }

    companion object {
        var melting: Effect? = null
    }
}
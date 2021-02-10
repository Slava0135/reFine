package refine.content

import mindustry.content.Liquids
import mindustry.ctype.ContentList
import mindustry.entities.bullet.BulletType
import mindustry.entities.bullet.LiquidBulletType

class ReBullets : ContentList {
    override fun load() {
        acidWave = object : LiquidBulletType(ReLiquids.acid) {
            init {
                damage = 4f
                drag = 0.01f
            }
        }

        acidTsunami = object : LiquidBulletType(Liquids.slag) {
            init {
                lifetime = 49f
                speed = 4f
                knockback = 1.3f
                puddleSize = 8f
                orbSize = 4f
                damage = 4.75f
                drag = 0.001f
                ammoMultiplier = 0.4f
                statusDuration = 60f * 4f
            }
        }
    }

    companion object {
        var acidTsunami: BulletType? = null
        var acidWave: BulletType? = null
    }
}
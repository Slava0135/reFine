package refine.content

import mindustry.entities.bullet.BulletType
import mindustry.entities.bullet.LiquidBulletType

class ReBullets {

    companion object {
        lateinit var acidTsunami: BulletType
        lateinit var acidWave: BulletType
    }
    fun load() {
        acidWave = object : LiquidBulletType(ReLiquids.acid) {}.apply {
            damage = 4f;
            drag = 0.01f;
        }

        acidTsunami = object : LiquidBulletType(ReLiquids.acid) {}.apply {
            lifetime = 49f;
            speed = 4f;
            knockback = 1.3f;
            puddleSize = 8f;
            orbSize = 4f;
            damage = 4.75f;
            drag = 0.001f;
            ammoMultiplier = 0.4f;
            statusDuration = 60f * 4f;
        }
    }
}
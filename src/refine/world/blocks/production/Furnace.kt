package refine.world.blocks.production

import arc.Core.atlas
import arc.graphics.Color
import arc.graphics.g2d.Draw
import arc.graphics.g2d.Fill
import arc.graphics.g2d.TextureRegion
import arc.math.Mathf
import arc.util.Time
import arc.util.io.Reads
import arc.util.io.Writes
import refine.content.ReItems
import mindustry.Vars
import mindustry.content.Fx
import mindustry.ctype.ContentType
import mindustry.gen.Building
import mindustry.gen.Sounds
import mindustry.graphics.Drawf
import mindustry.graphics.Pal
import mindustry.type.Item
import mindustry.ui.Bar
import mindustry.world.Block
import mindustry.world.meta.Stat
import mindustry.world.meta.StatUnit

open class Furnace(name: String) : Block(name) {

    val flameColor = Color.valueOf("ffc999")
    lateinit var topRegion: TextureRegion

    var fuelFlammability = 0.5f
    var smeltTime = 30f
    var fuelMultiplier = 10
    var smeltEffect = Fx.smeltsmoke
    var updateEffectChance = 0.04

    override fun setStats() {
        super.setStats()
        stats.add(Stat.productionTime, smeltTime / 60f, StatUnit.seconds)

        stats.add(Stat.input, ReItems.rawCopper)
        stats.add(Stat.input, ReItems.rawLead)
        stats.add(Stat.input, ReItems.rawTitanium)
    }

    override fun setBars() {
        super.setBars()
        bars.add("back") { entity: FurnaceBuild -> Bar("bar.input", Pal.lightOrange) { entity.fuelProgress } }
    }

    override fun init() {
        super.init()
        update = true
        solid = true
        hasItems = true
        sync = true
        ambientSound = Sounds.smelter
        ambientSoundVolume = 0.07f
    }

    override fun load() {
        super.load();
        topRegion = atlas.find("$name-top")
    }

    override fun outputsItems() = true

    inner class FurnaceBuild : Building() {
        var progress = 0f
        var warmup = 0f
        var fuelProgress = 0f

        override fun shouldConsume() = enabled && warmup > 0
        override fun shouldAmbientSound() = enabled && warmup > 0

        override fun draw() {
            super.draw()

            if (warmup > 0) {
                val g = 0.3f
                val r = 0.06f
                val cr = Mathf.random(0.1f)

                Draw.alpha((1f - g + Mathf.absin(Time.time, 8f, g) + Mathf.random(r) - r) * warmup)

                Draw.tint(flameColor)
                Fill.circle(x, y, 3f + Mathf.absin(Time.time, 5f, 2f) + cr)
                Draw.color(1f, 1f, 1f, warmup)
                Draw.rect(topRegion, x, y)
                Fill.circle(x, y, 1.9f + Mathf.absin(Time.time, 5f, 1f) + cr)

                Draw.color()
            }
        }

        override fun drawLight() {
            Drawf.light(team, x, y, (60f + Mathf.absin(10f, 5f)) * warmup * block.size, flameColor, 0.65f)
        }

        override fun getMaximumAccepted(item: Item?): Int {
            return when {
                item!!.name.contains("raw") -> {
                    val ore = getOre()
                    if (ore == null || ore == item) itemCapacity - items[item]
                    else 0
                }
                item.flammability > fuelFlammability -> {
                    val fuel = getFuel()
                    if (fuel == null || fuel == item) itemCapacity - items[fuel]
                    else 0
                }
                else -> 0
            }
        }

        override fun updateTile() {
            if (enabled) {
                if (fuelProgress > 0) {
                    fuelProgress -= getProgressIncrease(smeltTime / fuelMultiplier)
                    if (Mathf.chanceDelta(updateEffectChance)) {
                        smeltEffect.at(getX() + Mathf.range(size * 4f), getY() + Mathf.range(size * 4))
                    }
                    if (getOre() == null) {
                        progress = 0f
                        return
                    } else {
                        warmup = Mathf.lerpDelta(warmup, 1f, 0.02f)
                        progress += getProgressIncrease(smeltTime)
                    }
                } else {
                    if (getOre() != null) {
                        val fuel = getFuel()
                        fuel?.let {
                            fuelProgress = 1f
                            items.remove(fuel, 1)
                        }
                    }
                }
            }

            if (!enabled || fuelProgress < 0) {
                warmup = Mathf.lerp(warmup, 0f, 0.02f)
                return
            }

            if (progress >= 1f) {
                val ore = getOre()
                ore?.let {
                    items.remove(it, 1)
                    offload(Vars.content.getByName(ContentType.item, it.name.removePrefix("raw-")))
                }
                progress = 0f
            }
        }

        fun getFuel(): Item? {
            var item: Item? = null
            items.each { i, _ ->
                if (i.flammability > fuelFlammability) item = i
            }
            return item
        }

        fun getOre(): Item? {
            var item: Item? = null
            items.each { i, _ ->
                if (i.name.startsWith("raw")) item = i
            }
            return item
        }

        override fun write(write: Writes?) {
            super.write(write)
            write!!.f(progress)
            write.f(warmup)
            write.f(fuelProgress)
        }

        override fun read(read: Reads?, revision: Byte) {
            super.read(read, revision)
            progress = read!!.f()
            warmup = read.f()
            fuelProgress = read.f()
        }
    }
}
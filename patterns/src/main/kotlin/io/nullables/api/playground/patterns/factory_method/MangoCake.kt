package io.nullables.api.playground.patterns.factory_method

/**
 * Created by Inno Fang on 2017/9/1.
 */
class MangoCake : Cake {

    override fun prepareMaterials() {
        println("prepare Mango Cream")
    }

    override fun baking() {
        println("Baking ten minutes")
    }
}

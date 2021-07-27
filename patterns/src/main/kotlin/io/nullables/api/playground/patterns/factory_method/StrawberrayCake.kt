package io.nullables.api.playground.patterns.factory_method

/**
 * Created by Inno Fang on 2017/9/1.
 */
class StrawberrayCake : Cake {

    override fun prepareMaterials() {
        println("prepare Strawberry Cream")
    }

    override fun baking() {
        println("Baking fifteen minutes")
    }
}

package io.nullables.api.playground.patterns.factory_method

/**
 * Created by Inno Fang on 2017/9/1.
 */
fun main(args: Array<String>) {
    val factory: Factory = CakeFactory()
    val strawberryCake = factory.createProduct(StrawberrayCake::class.java)?.apply {
        prepareMaterials()
        baking()
    }

    println()

    val mangoCkae = factory.createProduct(MangoCake::class.java)?.apply {
        prepareMaterials()
        baking()
    }
}

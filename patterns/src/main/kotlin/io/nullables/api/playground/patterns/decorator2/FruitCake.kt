package io.nullables.api.playground.patterns.decorator2

/**
 * Created by Inno Fang on 2017/9/4.
 */
class FruitCake
constructor(cake: Cake) : DecorateCake(cake) {
    override fun make() {
        addSomeFruit()
        super.make()
    }

    private fun addSomeFruit() {
        println("Add some fruit")
    }
}

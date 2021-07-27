package io.nullables.api.playground.patterns.decorator2

/**
 * Created by Inno Fang on 2017/9/4.
 */
fun main(args: Array<String>) {
    val cake: Cake = CakeEmbryo()
    cake.make()

    /* Decorate Fruit Cake */
    println("\n+--- Decorate Fruit Cake ---+")
    val fruitCake = FruitCake(cake)
    fruitCake.make()
}

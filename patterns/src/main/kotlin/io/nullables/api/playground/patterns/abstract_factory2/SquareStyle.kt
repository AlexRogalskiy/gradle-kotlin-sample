package io.nullables.api.playground.patterns.abstract_factory2

/**
 * Created by Inno Fang on 2017/9/2.
 */
class SquareStyle : CakeStyle() {
    override fun style() {
        println("Square Style")
    }
}

package io.nullables.api.playground.patterns.strategy2

/**
 * Created by Inno Fang on 2017/10/24.
 */
class GoToEMei : GoToStrategy {
    override fun transportation() {
        println("take train")
    }
}

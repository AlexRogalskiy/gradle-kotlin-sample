package io.nullables.api.playground.patterns.abstract_factory2

/**
 * Created by Inno Fang on 2017/9/2.
 */
abstract class CakeFactory {
    abstract fun cream(): CakeCream
    abstract fun style(): CakeStyle
}

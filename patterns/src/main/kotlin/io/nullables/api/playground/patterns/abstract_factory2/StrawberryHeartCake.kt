package io.nullables.api.playground.patterns.abstract_factory2

/**
 * Created by Inno Fang on 2017/9/2.
 */
class StrawberryHeartCake : CakeFactory() {
    override fun style(): CakeStyle {
        return HeartStyle()
    }

    override fun cream(): CakeCream {
        return StrawberryCream()
    }
}

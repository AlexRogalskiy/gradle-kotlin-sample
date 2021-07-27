package io.nullables.api.playground.patterns.strategy2

/**
 * Created by Inno Fang on 2017/10/24.
 *
 *
 * Context 类
 */
class Wang {

    private var _goToStrategy: GoToStrategy? = null

    private var goToStrategy: GoToStrategy?
        get() = _goToStrategy
        set(value) {
            _goToStrategy = value
        }

    fun take() {
        goToStrategy?.transportation()
    }

    fun setGoToStrategy(goToHaiNan: GoToHaiNan) {
        this.goToStrategy = goToHaiNan
    }
}

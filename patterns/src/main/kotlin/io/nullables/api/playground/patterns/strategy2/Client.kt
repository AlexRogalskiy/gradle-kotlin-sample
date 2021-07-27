package io.nullables.api.playground.patterns.strategy2

/**
 * Created by Inno Fang on 2017/10/24.
 */

fun main(args: Array<String>) {
    val wang = Wang()
    //        wang.setGoToStrategy(new GoToChangChun());
    //        wang.setGoToStrategy(new GoToEMei());
    wang.setGoToStrategy(GoToHaiNan())
    wang.take()
}


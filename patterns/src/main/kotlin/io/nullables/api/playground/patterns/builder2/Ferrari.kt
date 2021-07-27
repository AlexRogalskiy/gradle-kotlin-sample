package io.nullables.api.playground.patterns.builder2

/**
 * Created by Inno Fang on 2017/8/31.
 *
 * You must set default value for all arguments
 */
data class Ferrari constructor(var color: String = "", var licensePlate: String = "", var brand: String = "") {

    companion object {
        fun build(init: Ferrari.() -> Unit) = Ferrari().apply(init)
    }
}

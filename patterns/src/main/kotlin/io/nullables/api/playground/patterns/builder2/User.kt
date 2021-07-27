package io.nullables.api.playground.patterns.builder2

/**
 * Created by Inno Fang on 2017/8/31.
 */
data class User constructor(var id: String = "", var name: String = "") {

    companion object {
        fun build(buildUser: User.() -> Unit) = User().apply(buildUser)
    }
}

fun main(args: Array<String>) {
    val user = User.build {
        id = "123"
        name = "Inno Fang"
    }

    println(user)
}

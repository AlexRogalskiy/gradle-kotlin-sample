package io.nullables.api.playground.patterns.parent_call

class Greeting(val word: String)

open class Father {
    init {
        sayGreeting()
    }

    open fun sayGreeting() = println("Luke! I'm your Father!!!")
}

class Leia : Father() {
    private var greeting: Greeting? = null
        get() {
            if (field == null) {
                field = Greeting("I love you!")
            }
            return field
        }

    override fun sayGreeting() = println(greeting!!.word)
}

package io.nullables.api.playground.patterns.delegation2

interface Engine {
    fun printEngine()
}

class Diesel(val power: Int) : Engine {
    override fun printEngine() {
        println("Diesel $power")
    }
}

class Car(e: Engine) : Engine by e

fun main(args: Array<String>) {
    val engine = Diesel(10)
    Car(engine).printEngine()
}

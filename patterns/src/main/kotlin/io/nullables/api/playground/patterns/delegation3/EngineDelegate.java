package io.nullables.api.playground.patterns.delegation3;

import kotlin.lazy
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class EngineDelegate {
    private val speedLimit : Int = 5

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return if (thisRef is HybridCar && thisRef.speed < speedLimit) "Electric" else "Gas"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been manually set to '${property.name}' in $thisRef.")
    }
}

class HybridCar(var speed : Int) {
    var engine: String by EngineDelegate()
}

class LazyCar {
    val engine: String by lazy {
        println("computed!")
        "Gas"
    }
}

class SmartCar {
    var speed : Int by Delegates.observable(10, {
        prop, old, new -> println("Changed from $old to $new")
    })
}

class SerializedCar (val map: Map<String, Any?>) {
    val speed : Int by map
    val engine : String by map
}

fun main(args: Array<String>) {
    val car = HybridCar(10)
    println(car.engine) // output: Gas
    car.speed = 2
    println(car.engine) // output: Electric

    val smartCar = SmartCar()
    smartCar.speed = 10;
    smartCar.speed++

    val map = HashMap<String, Any?>()
    map.put("speed", 10)
    map.put("engine", "Gas")
    var serializedCar = SerializedCar(map)
    println("Car with engine ${serializedCar.engine} and speed ${serializedCar.speed}")
}

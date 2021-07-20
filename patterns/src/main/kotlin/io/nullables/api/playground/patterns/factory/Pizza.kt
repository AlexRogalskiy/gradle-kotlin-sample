/*
 * Copyright (C) 2021. Alexander Rogalskiy. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.nullables.api.playground.patterns.factory;

interface Pizza {

    fun prepare()

    fun bake()

    fun cut()

    fun box()
}

class CheesePizza : Pizza {
    override fun prepare() = println("Preparing cheese pizza")
    override fun bake() = println("Baking cheese pizza")
    override fun cut() = println("Cutting cheese pizza")
    override fun box() = println("Packaging cheese pizza")
}

class PepperoniPizza : Pizza {
    override fun prepare() = println("Preparing pepperoni pizza")
    override fun bake() = println("Baking pepperoni pizza")
    override fun cut() = println("Cutting pepperoni pizza")
    override fun box() = println("Packaging pepperoni pizza")
}

enum class PizzaType {
    Cheese, Pepperoni
}

object PizzaStore {
    fun orderPizza(type: PizzaType) = when (type) {
        PizzaType.Cheese -> CheesePizza()
        PizzaType.Pepperoni -> PepperoniPizza()
    }
}

fun main(args: Array<String>) {
    val myPizza = PizzaStore.orderPizza(PizzaType.Cheese)

    myPizza.prepare()
    myPizza.bake()
    myPizza.cut()
    myPizza.box()
}

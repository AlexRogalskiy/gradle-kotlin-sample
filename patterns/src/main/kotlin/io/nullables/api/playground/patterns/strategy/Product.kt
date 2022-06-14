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
package io.nullables.api.playground.patterns.strategy

class Product(val name: String, val price: Double)

inline fun getTotal(productList: List<Product>, strategy: (price: Double) -> Double): Double {
    return productList.sumOf { strategy(it.price) }
}

fun normalStrategy(price: Double) = price

fun main(args: Array<String>) {
    val productA = Product("Product A", 10.0)
    val productB = Product("Product B", 25.6)

    val products = listOf(productA, productB)

    println(getTotal(products, ::normalStrategy))

    val discount = 25
    println(getTotal(products) {
        it - it * discount / 100
    })
}

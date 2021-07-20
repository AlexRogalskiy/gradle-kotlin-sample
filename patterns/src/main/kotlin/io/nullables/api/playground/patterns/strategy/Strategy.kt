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

class Product2(val name: String, val price: Double)

interface BillingStrategy {
    fun getTotal(productList: List<Product2>): Double
}

class NormalStrategy : BillingStrategy {
    override fun getTotal(productList: List<Product2>): Double {
        return productList.sumOf { it.price }
    }
}

class DiscountStrategy(private val discount: Double) : BillingStrategy {
    override fun getTotal(productList: List<Product2>): Double {
        return productList.sumOf { it.price } * (1 - discount / 100)
    }
}

fun main(args: Array<String>) {
    val productA = Product2("Product A", 10.0)
    val productB = Product2("Product B", 25.6)

    val products = listOf(productA, productB)

    val normalStrategy = NormalStrategy()
    println(normalStrategy.getTotal(products))

    val discountStrategy = DiscountStrategy(10.0)
    println(discountStrategy.getTotal(products))
}

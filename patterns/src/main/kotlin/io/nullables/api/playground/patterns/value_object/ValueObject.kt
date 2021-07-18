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
package io.nullables.api.playground.patterns.value_object

data class ValueObject(val firstValue: Int, val secondValue: Int)

fun main(args: Array<String>) {
    val v1 = ValueObject(1, 2)
    val v2 = ValueObject(1, 2)
    val v3 = ValueObject(2, 2)

    println("$v1 and $v2 is equal ${v1 == v2}")
    println("$v1 and $v3 is equal ${v1 == v3}")
}

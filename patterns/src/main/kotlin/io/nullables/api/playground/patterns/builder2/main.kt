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
package io.nullables.api.playground.patterns.builder2

/**
 * Created by Inno Fang on 2017/8/31.
 */
fun main(args: Array<String>) {
    val ferrari = Ferrari.build {
        brand = "ferrari"
        color = "red"
        licensePlate = "B88888"
    }

    println(ferrari)

    val audi = Audi.build {
        brand = "Audi"
        color = "blue"
        licensePlate = "C88888"
    }
    println(audi)
}

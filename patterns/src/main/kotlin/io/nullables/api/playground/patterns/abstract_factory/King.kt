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
package io.nullables.api.playground.patterns.abstract_factory

interface King {
    fun getDescription(): String
}

interface Army {
    fun getDescription(): String
}

interface KindomFactory {
    fun createKing(): King
    fun createArmy(): Army
}

class NorthKing : King {
    override fun getDescription() = "King Stark"
}

class RockKing : King {
    override fun getDescription() = "King Lannister"
}

class NorthArmy : Army {
    override fun getDescription() = "North army"
}

class RockArmy : Army {
    override fun getDescription() = "Rock army"
}

class NorthFactory : KindomFactory {
    override fun createKing() = NorthKing()
    override fun createArmy() = NorthArmy()
}

class RockFactory : KindomFactory {
    override fun createKing() = RockKing()
    override fun createArmy() = RockArmy()
}

fun main(args: Array<String>) {
    val factory = if (args.isEmpty())
        NorthFactory()
    else
        RockFactory()

    val king = factory.createKing()
    val army = factory.createArmy()

    println("King: ${king.getDescription()}")
    println("Army: ${army.getDescription()}")
}

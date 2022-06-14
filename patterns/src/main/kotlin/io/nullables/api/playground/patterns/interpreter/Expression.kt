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
package io.nullables.api.playground.patterns.interpreter

interface Expression {
    fun interpret(): String
}

@OptIn(ExperimentalUnsignedTypes::class)
class IntToBinaryExpression(val input: Int) : Expression {
    override fun interpret() = input.toUInt().toString(radix = 2)
}

@OptIn(ExperimentalUnsignedTypes::class)
class IntToHexExpression(val input: Int) : Expression {
    override fun interpret() = input.toUInt().toString(radix = 16)
}

class Interpreter {
    fun interpret(s: String) = getInterpreter(s).interpret()

    fun getInterpreter(s: String): Expression = when {
        s.contains("Binary") -> IntToBinaryExpression(s.substring(0, s.indexOf(' ')).toInt())
        s.contains("Hexadecimal") -> IntToHexExpression(s.substring(0, s.indexOf(' ')).toInt())
        else -> object : Expression {
            override fun interpret() = s
        }
    }
}

fun main(args: Array<String>) {
    val str1 = "28 in Binary"
    val str2 = "28 in Hexadecimal"
    val str3 = "Random unsupported string"

    val interpreter = Interpreter()

    println("$str1 = ${interpreter.interpret(str1)}")
    println("$str2 = ${interpreter.interpret(str2)}")
    println("$str3 = ${interpreter.interpret(str3)}")
}

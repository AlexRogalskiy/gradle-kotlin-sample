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
package io.nullables.api.playground.patterns.observer

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class User {
    // Observe and prints any changes in `age`
    private var age: Int by Delegates.observable(AGE_UNDEFINED) { prop, old, new ->
        println("Old age: $old, New age: $new")
    }

    // Automatically updates `age` when set birthDate
    var birthDate: Date by Delegates.observable(Date()) { _, _, new ->
        age = new.getAge()
    }

    companion object {
        const val AGE_UNDEFINED = -1
    }
}

fun Date.getAge(): Int {
    val now = Date()
    val formatter = SimpleDateFormat("yyyyMMdd", Locale.getDefault())

    return (formatter.format(now).toInt() - formatter.format(this).toInt()) / 10000
}

fun main(args: Array<String>) {
    fun createDate(date: String): Date {
        return try {
            val formatter = SimpleDateFormat("mm-DD-yyyy", Locale.getDefault())
            formatter.parse(date)
        } catch (e: ParseException) {
            Date() // only for testing purpose =p never do that!
        }
    }

    val user = User()

    user.birthDate = Date()
    user.birthDate = createDate("09-12-1990")
    user.birthDate = createDate("27-08-1995")
}

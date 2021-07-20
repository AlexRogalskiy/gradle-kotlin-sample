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
package io.nullables.api.playground.patterns.builder

import java.util.*

class User private constructor(
    val username: String?,
    val password: String?,
    val birthday: Date?
) {

    data class Builder(
        var username: String? = null,
        var password: String? = null,
        var birthday: Date? = null
    ) {

        fun username(username: String) = apply { this.username = username }
        fun password(password: String) = apply { this.password = password }
        fun birthday(birthday: Date) = apply { this.birthday = birthday }
        fun build() = User(username, password, birthday)
    }
}

val adminUser = User.Builder()
    .username("admin")
    .password("strictAdm!nP@ssword")
    .birthday(Date(1990, 1, 1))
    .build()

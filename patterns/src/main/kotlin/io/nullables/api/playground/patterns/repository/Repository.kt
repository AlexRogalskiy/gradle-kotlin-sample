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
package io.nullables.api.playground.patterns.repository

interface Repository<T, IDTYPE> {
    fun add(entity: T)
    fun remove(id: IDTYPE)
    fun getAll(): List<T>
    fun getById(id: IDTYPE): T?
}

data class User(val username: String, val password: String)

class UserRepository : Repository<User, String> {
    private val users = mutableMapOf<String, User>()

    override fun add(entity: User) {
        users[entity.username] = entity
    }

    override fun remove(id: String) {
        users.remove(id)
    }

    override fun getAll(): List<User> = users.values.toList()
    override fun getById(id: String): User? = users[id]
}

fun main(args: Array<String>) {
    val repository = UserRepository()

    repository.add(User("user1", "pass"))
    repository.add(User("user2", "pass"))
    repository.add(User("user3", "pass"))
    repository.add(User("user4", "pass"))

    println("get by id user2: ${repository.getById("user2")}")
    println("All users before remove: ${repository.getAll()}")

    repository.remove("user3")

    println("All users after remove user 3: ${repository.getAll()}")
}

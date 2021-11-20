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
package io.nullables.api.playground.patterns.proxy2

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy

/**
 * Created by Inno Fang on 2017/9/3.
 */
inline fun <reified T : Any> createProxy(obj: Any) = createProxy(obj, T::class.java)

inline fun <reified T : Any> createProxy(inv: InvocationHandler) = createProxy(T::class.java, inv)

fun <T> createProxy(obj: Any, clazz: Class<T>): T {
    val loader = clazz.classLoader
    val interfaces = arrayOf(clazz)
    return clazz.cast(Proxy.newProxyInstance(loader, interfaces) { _, method, args ->
        method.invoke(obj, *(args ?: arrayOfNulls<Any>(0)))
    })
}

fun <T> createProxy(clazz: Class<T>, inv: InvocationHandler): T {
    val loader = clazz.classLoader
    val interfaces = arrayOf(clazz)
    return clazz.cast(Proxy.newProxyInstance(loader, interfaces, inv))
}

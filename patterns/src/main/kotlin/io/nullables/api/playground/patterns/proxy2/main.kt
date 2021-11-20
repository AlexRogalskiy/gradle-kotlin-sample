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

/**
 * Created by Inno Fang on 2017/9/3.
 */
fun main(args: Array<String>) {
    val picker: IPicker = RealPicker()
    val proxy = ProxyPicker(picker)

    proxy.receiveMessage()
    proxy.takeCourier()
    proxy.signatureAcceptance()

    println("\n+-----Splitter-----+\n")

    /* Dynamic Proxy */

    //implement 1
    /*
    val dynamicProxy = DynamicProxy(picker)
    val loader = picker.javaClass.classLoader
    val dynamicPicker = Proxy.newProxyInstance(
            loader, arrayOf(IPicker::class.java), dynamicProxy) as IPicker
    */

    //implement 2
    /*
    val dynamicPicker = createProxy<IPicker>(InvocationHandler { _, method, args ->
        method!!.invoke(picker, *(args ?: arrayOfNulls(0)))
    })
    */

    //implement 3
    val dynamicPicker = createProxy<IPicker>(picker)

    dynamicPicker.receiveMessage()
    dynamicPicker.takeCourier()
    dynamicPicker.signatureAcceptance()
}

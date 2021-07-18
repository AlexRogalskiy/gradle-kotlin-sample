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
package io.nullables.api.playground.patterns.proxy

interface Image {
    fun getImageUrl(): String
}

class CachedImage : Image {
    private var cachedImageUrl = ""
    private val realImage = RealImage()

    override fun getImageUrl(): String {
        if (cachedImageUrl.isEmpty()) {
            cachedImageUrl = realImage.getImageUrl()
        }

        return cachedImageUrl
    }
}

class RealImage : Image {
    private var imageUrl: String = ""

    override fun getImageUrl(): String {
        this.queryImageService()

        return imageUrl
    }

    private fun queryImageService() {
        println("calling service...")
        Thread.sleep(5000)
        imageUrl = "http://localhost/somepage/anyimage.jpg"
    }
}

fun main(args: Array<String>) {
    val cachedImage = CachedImage()

    println("Getting image for the first time and cache it")
    println("first time ${cachedImage.getImageUrl()}")
    println("second time ${cachedImage.getImageUrl()}")
}

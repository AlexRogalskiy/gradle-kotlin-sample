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
package io.nullables.api.playground.patterns.prototype2

/**
 * Created by Inno Fang on 2017/8/30.
 */
class WordDocument
constructor(var text: String = "", var images: ArrayList<String> = ArrayList<String>()) : Cloneable {

    init {
        println("----- init -----")
    }

    fun addImage(image: String) {
        images.add(image)
    }

    fun showDocument() {
        println("---- start -----")
        println("Text: " + text)
        println("Image List : ")
        images.map { println("Image name : $it") }
        println("----- End ------")
    }

    fun cloneTo(): WordDocument? {
        try {
            val copy: WordDocument = super.clone() as WordDocument
            copy.text = this.text
            copy.images = this.images.clone() as ArrayList<String>
            return copy
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return null
    }
}

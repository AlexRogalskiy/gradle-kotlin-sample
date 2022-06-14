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
package io.nullables.api.playground.patterns

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates

interface TextChangedListener {

    fun onTextChanged(oldText: String, newText: String)
}

class PrintingTextChangedListener : TextChangedListener {

    var text = ""

    override fun onTextChanged(oldText: String, newText: String) {
        text = "Text is changed: $oldText -> $newText"
    }
}

class TextView {

    val listeners = mutableListOf<TextChangedListener>()

    var text: String by Delegates.observable("<empty>") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}

class ListenerTest {

    @Test
    fun Listener() {
        val listener = PrintingTextChangedListener()

        val textView = TextView().apply {
            listeners.add(listener)
        }

        with(textView) {
            text = "Lorem ipsum"
            text = "dolor sit amet"
        }

        assertThat(listener.text).isEqualTo("Text is changed: Lorem ipsum -> dolor sit amet")
    }
}

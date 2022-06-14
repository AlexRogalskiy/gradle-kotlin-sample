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
package io.nullables.api.playground.algorithms.math

import io.nullables.api.playground.algorithms.sqrt
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewtonMethodKtTest {
    @Test
    fun sqrt() {
        assertTrue(sqrt(-2.0).isNaN())
        assertTrue(sqrt(-2.0, 0.toDouble()).isNaN())
        assertEquals(2.toDouble(), sqrt(4.toDouble()), 1e-15)
        assertEquals(4.toDouble(), sqrt(16.toDouble()), 1e-12)
        for (i in 7 until 99) {
            for (j in -15 until -1) {
                assertEquals(sqrt(i.toDouble()), sqrt(i.toDouble()), j.toDouble())
            }
        }
    }

    @Test
    fun sqrt1() {
        assertTrue(sqrt(-2).isNaN())
        assertEquals(2.toDouble(), sqrt(4), 1e-15)
        assertEquals(4.toDouble(), sqrt(16), 1e-12)
        for (i in 7 until 99) {
            for (j in -15 until -1) {
                assertEquals(sqrt(i), sqrt(i), j.toDouble())
            }
        }
    }
}

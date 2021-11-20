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

import io.nullables.api.playground.algorithms.gcd
import io.nullables.api.playground.algorithms.lcm
import org.junit.Assert.assertEquals
import org.junit.Test

class GcdKtTest {
    @Test
    fun gcd() {
        assertEquals(1, gcd(1, 1))
        assertEquals(8, gcd(24, 16))
        assertEquals(8, gcd(16, 24))
        assertEquals(16, gcd(16, 16))
        assertEquals(1, gcd(13, 29))
        assertEquals(8, gcd(40, 16))
        assertEquals(5, gcd(40, 15))
    }

    @Test
    fun lcm() {
        assertEquals(1, lcm(1, 1))
        assertEquals(48, lcm(24, 16))
        assertEquals(48, lcm(16, 24))
        assertEquals(16, lcm(16, 16))
        assertEquals(377, lcm(13, 29))
        assertEquals(80, lcm(40, 16))
        assertEquals(120, lcm(40, 15))
        assertEquals(1000000, lcm(1000000, 1000000))
    }
}

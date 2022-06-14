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

import io.nullables.api.playground.algorithms.binomial
import org.junit.Assert
import org.junit.Test

class BinomialTest {
    @Test
    fun test1() {
        Assert.assertEquals(0, binomial(0, 1))
        Assert.assertEquals(1, binomial(1, 1))
        Assert.assertEquals(2, binomial(2, 1))
        Assert.assertEquals(3, binomial(3, 1))
        Assert.assertEquals(3, binomial(3, 2))
        Assert.assertEquals(4, binomial(4, 1))

        Assert.assertEquals(1, binomial(5, 0))
        Assert.assertEquals(5, binomial(5, 1))
        Assert.assertEquals(10, binomial(5, 2))
        Assert.assertEquals(10, binomial(5, 3))
        Assert.assertEquals(5, binomial(5, 4))
        Assert.assertEquals(1, binomial(5, 5))

        Assert.assertEquals(1, binomial(6, 0))
        Assert.assertEquals(6, binomial(6, 1))
        Assert.assertEquals(15, binomial(6, 2))
        Assert.assertEquals(20, binomial(6, 3))
        Assert.assertEquals(15, binomial(6, 4))
        Assert.assertEquals(6, binomial(6, 5))
        Assert.assertEquals(1, binomial(6, 6))
    }
}

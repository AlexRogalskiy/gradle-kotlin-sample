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
package io.nullables.api.playground.patterns.private_class

import java.math.BigDecimal

class SalaryCalculator(baseSalary: BigDecimal, bonuses: BigDecimal, overtime: BigDecimal) {

    private val salaryData: SalaryData = SalaryData(baseSalary, bonuses, overtime)

    val income: BigDecimal
        get() = salaryData.basePay.add(salaryData.bonuses.add(salaryData.overtime))

    internal inner class SalaryData(
        val basePay: BigDecimal,
        val bonuses: BigDecimal,
        val overtime: BigDecimal
    )
}

fun main(args: Array<String>) {
    val salaryCalculator = SalaryCalculator(BigDecimal(1000), BigDecimal(100), BigDecimal(40))
    println(salaryCalculator.income)
}

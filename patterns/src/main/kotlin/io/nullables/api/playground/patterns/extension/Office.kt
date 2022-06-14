package io.nullables.api.playground.patterns.extension

open class Office(var space : Int)

fun Office.hasTable() = true

fun Office.plus(right : Office) : Office {
    return Office(this.space + right.space)
}

fun main(args: Array<String>) {
    val myOffice = Office(1)
    println("Office has table ${myOffice.hasTable()}") // output: Office has table yes
}

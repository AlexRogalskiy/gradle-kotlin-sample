package io.nullables.api.playground.patterns.decorator2

/**
 * Created by Inno Fang on 2017/9/4.
 */
open class DecorateCake constructor(val cake: Cake) : Cake by cake

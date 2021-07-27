package io.nullables.api.playground.patterns.proxy2

/**
 * Created by Inno Fang on 2017/9/3.
 */
class ProxyPicker
constructor(private val picker: IPicker) : IPicker by picker

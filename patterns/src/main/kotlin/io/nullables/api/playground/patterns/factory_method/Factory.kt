package io.nullables.api.playground.patterns.factory_method

/**
 * Created by Inno Fang on 2017/9/1.
 */
abstract class Factory {
    abstract fun <T : Cake> createProduct(clz: Class<T>): T?
}

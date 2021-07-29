package io.nullables.api.playground.algorithms

abstract class AbstractSearchStrategy<T> {
    abstract fun perform(arr: Array<T>, element: T): Int
}

package io.nullables.api.playground.algorithms

abstract class AbstractSortStrategy {
    abstract fun<T : Comparable<T>> perform(arr: Array<T>)
}

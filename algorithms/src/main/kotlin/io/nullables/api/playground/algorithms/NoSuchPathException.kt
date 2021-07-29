package io.nullables.api.playground.algorithms

class NoSuchPathException(s: String?) : Exception(s) {
    constructor() : this(null)
}

package io.nullables.api.playground.algorithms

interface ConvexHullAlgorithm {
    fun convexHull(points: Array<Point>): Collection<Point>
}

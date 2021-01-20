package io.nullables.api.sample.testflow.rules

import io.nullables.api.sample.testflow.utils.createEnvironment
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.spekframework.spek2.dsl.Root
import org.spekframework.spek2.lifecycle.CachingMode

fun Root.setupKotlinEnvironment() {
    val wrapper by memoized(CachingMode.SCOPE, { createEnvironment() }, { it.dispose() })

    @Suppress("UNUSED_VARIABLE") // name is used for delegation
    val env: KotlinCoreEnvironment by memoized(CachingMode.EACH_GROUP) { wrapper.env }
}
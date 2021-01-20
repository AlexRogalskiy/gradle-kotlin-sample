package io.nullables.api.sample.testflow.utils

import java.io.OutputStream

internal class NullOutputStream : OutputStream() {

    override fun write(b: Int) {
    }
}
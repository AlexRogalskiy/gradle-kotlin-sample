package io.nullables.api.sample.testflow.utils

import java.io.PrintStream

class NullPrintStream : PrintStream(NullOutputStream())
object Config {
    internal const val JSON_OUTPUT_FORMATTER = "json"
    internal const val BUILD_STABLE_REGEX = "^[0-9,.v-]+(-r)?$"
    internal const val KTLINT_COLOR_NAME = "RED"
    internal const val SPOTLESS_INDENT_WITH_SPACES = 4

    const val JUNIT5_KEY = "runnerBuilder"
    const val JUNIT5_VALUE = "de.mannodermaus.junit5.AndroidJUnit5Builder"

    const val ORCHESTRATOR_KEY = "clearPackageData"
    const val ORCHESTRATOR_VALUE = "true"
}

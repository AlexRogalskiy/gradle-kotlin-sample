object Versions {
    const val DETEKT_VERSION = "1.15.0"

    const val PROJECT_VERSION: String = "1.0.0"
    const val JVM_TARGET: String = "1.8"
    const val API_VERSION: String = "1.4"
    const val LANGUAGE_VERSION: String = "1.4"
    const val JACOCO: String = "0.8.6"
    const val KTLINT: String = "0.37.2"

    fun currentOrSnapshot(): String {
        if (System.getProperty("snapshot")?.toBoolean() == true) {
            return "$PROJECT_VERSION-SNAPSHOT"
        }
        return PROJECT_VERSION
    }
}

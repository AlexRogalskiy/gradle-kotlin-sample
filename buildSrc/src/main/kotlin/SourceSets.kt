//import org.gradle.api.NamedDomainObjectContainer
//import org.gradle.api.Project
//import org.gradle.api.tasks.SourceSet
//
///**
// * An object that includes source sets
// */
//private object InternalSourceSet {
//    const val MAIN = "main"
//    const val TEST = "test"
//    const val ANDROID_TEST = "androidTest"
//}
//
///**
// * The common interface to create any source set
// */
//@FunctionalInterface
//private interface SourceSetCreator {
//
//    /**
//     * The val which includes name of the source set from [InternalSourceSet]
//     */
//    val name: String
//
//    /**
//     * Creates the requested source set
//     *
//     * @param namedDomainObjectContainer The container to create the corresponding source set
//     * @param project The project
//     *
//     * @return The [AndroidSourceSet]
//     */
//    fun create(
//        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
//        project: Project
//    ): SourceSet
//}
//
///**
// * A [SourceSetCreator] implementation to create main Kotlin [AndroidSourceSet]
// */
//internal object Main : SourceSetCreator {
//    override val name = InternalSourceSet.MAIN
//
//    override fun create(
//        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
//        project: Project
//    ): SourceSet {
//        return namedDomainObjectContainer.getByName(name).apply {
//            java.srcDir("src/main/kotlin")
//        }
//    }
//}
//
///**
// * A [SourceSetCreator] implementation to create test Kotlin [AndroidSourceSet]
// */
//internal object Test : SourceSetCreator {
//    override val name = InternalSourceSet.TEST
//
//    override fun create(
//        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
//        project: Project
//    ): SourceSet {
//        return namedDomainObjectContainer.getByName(name).apply {
//            java.srcDirs("src/sharedTest/kotlin", "src/test/kotlin")
//            resources.srcDir("src/test/resources")
//        }
//    }
//}
//
///**
// * A [SourceSetCreator] implementation to create android test Kotlin [AndroidSourceSet]
// */
//internal object AndroidTest : SourceSetCreator {
//    override val name = InternalSourceSet.ANDROID_TEST
//
//    override fun create(
//        namedDomainObjectContainer: NamedDomainObjectContainer<SourceSet>,
//        project: Project
//    ): SourceSet {
//        return namedDomainObjectContainer.getByName(name).apply {
//            java.srcDirs("src/sharedTest/kotlin", "src/androidTest/kotlin")
//            resources.srcDir("src/androidTest/resources")
//        }
//    }
//}

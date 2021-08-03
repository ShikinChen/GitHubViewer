import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.kotlin.dsl.kotlin
import java.io.File
import java.io.FileInputStream
import java.util.*

val localPropertiesFile = File(rootDir, "debug.properties")
val properties = Properties()
properties.load(FileInputStream(localPropertiesFile))
val propertyNames = properties.propertyNames()
while (propertyNames.hasMoreElements()) {
    val propertyName = propertyNames.nextElement() as String
    project.extensions.add(propertyName, properties.getProperty(propertyName))
}
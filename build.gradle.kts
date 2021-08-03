// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    Android.addRepos(repositories)
    dependencies {
        classpath(Dependencies.kotlin.plugin)
        classpath(Dependencies.gradle.plugin)
        classpath(Dependencies.hilt.plugin)
        classpath(Dependencies.objectBox.plugin)
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("serialization", version = Versions.kotlin))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    Android.addRepos(repositories)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

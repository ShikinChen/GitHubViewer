package dependencies

/**
 *
 * @author shiki
 * @date 2021/7/29
 *
 */
object Hilt {
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltCore}"

    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltCore}"

    const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltCore}"
}
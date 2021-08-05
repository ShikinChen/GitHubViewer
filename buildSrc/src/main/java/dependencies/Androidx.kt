package dependencies

/**
 *
 * @author shiki
 * @date 2021/7/29
 *
 */
object Androidx {
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val startup = "androidx.startup:startup-runtime:${Versions.startup}"

    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val browser = "androidx.browser:browser:${Versions.browser}"
}
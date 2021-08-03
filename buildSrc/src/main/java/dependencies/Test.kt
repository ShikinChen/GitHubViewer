package dependencies

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
object Test {
    const val junit = "junit:junit:4.+"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"

    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}
package dependencies

/**
 *
 * @author shiki
 * @date 2021/7/29
 *
 */
object Compose {
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val icons = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val layout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val constraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint}"
    const val paging = "androidx.paging:paging-compose:${Versions.paging}"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"
}
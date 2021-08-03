package me.shiki.githubviewer.ui.main

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
sealed class NavScreen(val route: String) {
    object Home : NavScreen("Home")
    object DetailsRepo : NavScreen("DetailsRepo") {
        const val routeWithArgument: String = "DetailsRepo/{repoId}"
        const val argument0: String = "repoId"
    }
}
package me.shiki.githubviewer.ui.main

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
sealed class NavScreen(val route: String) {
    object Home : NavScreen("Home")
    object RepoDetails : NavScreen("RepoDetails") {
        const val routeWithArgument: String = "RepoDetails/{repoId}"
        const val repoId: String = "repoId"
    }

    object ReposList : NavScreen("ReposList")
    object FollowersList : NavScreen("FollowersList")
}
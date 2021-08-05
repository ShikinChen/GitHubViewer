package me.shiki.githubviewer.ui.main

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
class MainActions(navController: NavHostController) {
    val navigateToRepoDetails: (Long) -> Unit = { id ->
        NavScreen.RepoDetails.apply {
            navController.navigate(routeWithArgument.replace("{$repoId}", id.toString()))
        }
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
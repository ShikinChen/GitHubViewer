package me.shiki.githubviewer.ui.main

import androidx.navigation.NavHostController

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
class MainActions(navController: NavHostController) {
    val navigateToDetailsRepo: (Long) -> Unit = { id ->
        NavScreen.DetailsRepo.apply {
            navController.navigate(routeWithArgument.replace("{$argument0}", id.toString()))
        }
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
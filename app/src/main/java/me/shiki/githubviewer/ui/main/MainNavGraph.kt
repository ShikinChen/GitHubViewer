package me.shiki.githubviewer.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.paging.ExperimentalPagingApi
import com.google.accompanist.insets.ProvideWindowInsets
import me.shiki.githubviewer.ui.home.RepoDetails
import me.shiki.githubviewer.ui.home.ReposList
import me.shiki.githubviewer.ui.home.StartApp

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@ExperimentalPagingApi
@Composable
fun MainNavGraph(navController: NavHostController) {
    val actions = remember(navController) {
        MainActions(navController)
    }
    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(NavScreen.Home.route) {
                StartApp(viewModel = hiltViewModel(), navigateToDetailsRepo = actions.navigateToRepoDetails)
            }
            composable(
                route = NavScreen.RepoDetails.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.RepoDetails.repoId) {
                    type = NavType.LongType
                })
            ) { it ->
                it.arguments?.let {
                    RepoDetails(
                        repoId = it.getLong(NavScreen.RepoDetails.repoId),
                        viewModel = hiltViewModel(),
                        upPress = actions.upPress
                    )
                }
            }
        }
    }
}
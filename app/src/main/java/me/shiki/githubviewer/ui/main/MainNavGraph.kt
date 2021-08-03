package me.shiki.githubviewer.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.paging.ExperimentalPagingApi
import com.google.accompanist.insets.ProvideWindowInsets
import me.shiki.githubviewer.ui.home.DetailsRepo
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
                StartApp(viewModel = hiltViewModel(), navigateToDetailsRepo = actions.navigateToDetailsRepo)
            }
            composable(
                route = NavScreen.DetailsRepo.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.DetailsRepo.argument0) {
                    type = NavType.LongType
                })
            ) {
                it.arguments?.let {
                    DetailsRepo(
                        repoId = it.getLong(NavScreen.DetailsRepo.argument0),
                        viewModel = hiltViewModel(),
                        upPress = actions.upPress
                    )
                }
            }
        }
    }
}
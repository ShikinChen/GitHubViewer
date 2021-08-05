package me.shiki.githubviewer.ui.home

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import me.shiki.githubviewer.R
import me.shiki.githubviewer.base.LocalBaseViewModel
import me.shiki.githubviewer.ui.common.AppBar
import me.shiki.githubviewer.ui.main.NavScreen
import me.shiki.githubviewer.ui.theme.Purple700
import me.shiki.githubviewer.vm.HomeViewModel

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@ExperimentalPagingApi
@Composable
fun TabsHome(viewModel: HomeViewModel, navigateToDetailsRepo: (Long) -> Unit) {
    val tabs = HomeTab.values()
    val tabId by viewModel.selectedTab.collectAsState(0)
    val showSnackBar by LocalBaseViewModel.current.isShowSnackBar.collectAsState()
    val navController = rememberNavController()


    Scaffold(topBar = {
        Crossfade(HomeTab.getTabFromResource(tabId)) {
            when (it) {
                HomeTab.REPOS -> AppBar(title = stringResource(id = R.string.title_repos), isShowNavigationIcon = false)
                HomeTab.FOLLOWERS -> AppBar(
                    title = stringResource(id = R.string.title_followers),
                    isShowNavigationIcon = false
                )
            }
        }
    }, bottomBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                if (showSnackBar) {
                    SnackbarInfo()
                }
                BottomNavigation(
                    backgroundColor = Purple700, modifier = Modifier
                        .navigationBarsHeight(
                            56.dp
                        )
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                            selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                            onClick = {
                                navController.navigate(tab.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White,
                            modifier = Modifier
                                .navigationBarsPadding()
                        )
                    }
                }
            }
        }

    }) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        NavHost(navController, startDestination = NavScreen.ReposList.route) {
            composable(NavScreen.ReposList.route) {
                ReposList(
                    modifier = modifier,
                    viewModel = viewModel,
                    navigateToDetailsRepo = navigateToDetailsRepo
                )
            }
            composable(NavScreen.FollowersList.route) { FollowersList(modifier, viewModel = viewModel) }
        }
    }
}

enum class HomeTab(@StringRes val title: Int, val icon: ImageVector, val route: String) {
    REPOS(R.string.menu_repos, Icons.Filled.List, NavScreen.ReposList.route),
    FOLLOWERS(R.string.menu_followers, Icons.Filled.People, NavScreen.FollowersList.route);

    companion object {
        fun getTabFromResource(@StringRes resource: Int): HomeTab {
            return when (resource) {
                R.string.menu_repos -> REPOS
                R.string.menu_followers -> FOLLOWERS
                else -> REPOS
            }
        }
    }
}

@Composable
fun SnackbarInfo(modifier: Modifier = Modifier) {
    Column {
        Snackbar(modifier = modifier.padding(8.dp)) {
            Text(text = stringResource(id = R.string.exit_info))
        }
    }
}
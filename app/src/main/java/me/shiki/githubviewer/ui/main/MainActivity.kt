package me.shiki.githubviewer.ui.main

import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import me.shiki.githubviewer.base.LocalBaseViewModel
import me.shiki.githubviewer.di.StatusBarHeight
import me.shiki.githubviewer.ui.BaseActivity
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
import me.shiki.githubviewer.vm.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavHostController

    @StatusBarHeight
    var statusBarHeight: Int=0

    @ExperimentalPagingApi
    @Composable
    override fun content() {
        navController = rememberNavController()
        CompositionLocalProvider(LocalBaseViewModel provides viewModel) {
            GitHubViewerTheme {
                Surface {
                    MainNavGraph(navController = navController)
                }
            }
        }
    }
}

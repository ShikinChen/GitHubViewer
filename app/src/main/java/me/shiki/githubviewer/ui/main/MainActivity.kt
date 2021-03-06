package me.shiki.githubviewer.ui.main

import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.ImageLoader
import coil.compose.LocalImageLoader
import dagger.hilt.android.AndroidEntryPoint
import me.shiki.githubviewer.base.LocalBaseViewModel
import me.shiki.githubviewer.ui.BaseActivity
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
import me.shiki.githubviewer.vm.MainViewModel
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override lateinit var navController: NavHostController

    @ExperimentalTime
    @ExperimentalPagingApi
    @Composable
    override fun content() {
        navController = rememberNavController()
        CompositionLocalProvider(
            LocalBaseViewModel provides viewModel,
            LocalImageLoader provides imageLoader
        ) {
            GitHubViewerTheme {
                Surface {
                    MainNavGraph(navController = navController)
                }
            }
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.route) {
            NavScreen.Home.route -> if (viewModel.isShowSnackBar.value) {
                finishAffinity()
            } else {
                viewModel.toggleSnackBar()
            }
            else -> super.onBackPressed()
        }
    }
}

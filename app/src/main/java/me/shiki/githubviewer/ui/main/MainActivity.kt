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

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override lateinit var navController: NavHostController

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
}

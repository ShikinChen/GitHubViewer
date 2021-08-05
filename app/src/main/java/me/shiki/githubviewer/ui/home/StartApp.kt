package me.shiki.githubviewer.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.ExperimentalPagingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.shiki.githubviewer.ext.isError
import me.shiki.githubviewer.ext.isSucceeded
import me.shiki.githubviewer.ui.common.ErrorConnect
import me.shiki.githubviewer.vm.HomeViewModel

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@Composable
fun StartApp(viewModel: HomeViewModel, navigateToDetailsRepo: (Long) -> Unit) {
    val user by viewModel.loadingUser.collectAsState(viewModel.loadingUser.value)
    Box(Modifier.fillMaxSize()) {
        when {
            user.isSucceeded -> TabsHome(viewModel = viewModel, navigateToDetailsRepo = navigateToDetailsRepo)
            user.isError -> ErrorConnect { viewModel.repeatLoadingUser() }
            else -> Splash()
        }
    }
}

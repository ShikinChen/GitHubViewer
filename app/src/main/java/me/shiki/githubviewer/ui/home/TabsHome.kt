package me.shiki.githubviewer.ui.home

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.gyf.immersionbar.ImmersionBar
import me.shiki.githubviewer.R
import me.shiki.githubviewer.base.LocalBaseViewModel
import me.shiki.githubviewer.ext.px2dp
import me.shiki.githubviewer.model.Follower
import me.shiki.githubviewer.model.Repo
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
    val lazyRespos: LazyPagingItems<Repo> = viewModel.repos.collectAsLazyPagingItems()
    val lazyFollowers: LazyPagingItems<Follower> = viewModel.followers.collectAsLazyPagingItems()

    Scaffold(topBar = {
        Crossfade(HomeTab.getTabFromResource(tabId)) {
            when (it) {
                HomeTab.REPOS -> PosterAppBar(title = stringResource(id = R.string.title_repos))
                HomeTab.FOLLOWERS -> PosterAppBar(title = stringResource(id = R.string.title_followers))
            }
        }
    }, bottomBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                if (showSnackBar) {
                    SnackbarInfo()
                }
                BottomNavigation(
                    backgroundColor = Purple700, modifier = Modifier.navigationBarsHeight(
                        56.dp
                    )
                ) {
                    tabs.forEach {
                        BottomNavigationItem(
                            icon = { Icon(imageVector = it.icon, contentDescription = null) },
                            selected = it == HomeTab.getTabFromResource(tabId),
                            onClick = { viewModel.selectTab(it.title) },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        }

    }) {
        val modifier = Modifier.padding(it)
        Crossfade(targetState = HomeTab.getTabFromResource(tabId)) {
            when (it) {
                HomeTab.REPOS -> ReposList(modifier, lazyRespos, navigateToDetailsRepo)
                HomeTab.FOLLOWERS -> ListFollowers(modifier, lazyFollowers)
            }
        }
    }
}

enum class HomeTab(@StringRes val title: Int, val icon: ImageVector) {
    REPOS(R.string.menu_repos, Icons.Filled.List),
    FOLLOWERS(R.string.menu_followers, Icons.Filled.People);

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
fun PosterAppBar(title: String) {
    val context = LocalContext.current
    val statusBarHeight = ImmersionBar.getStatusBarHeight(context as Activity).px2dp()
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Purple700,
        modifier = Modifier.navigationBarsHeight((58 + statusBarHeight).dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(statusBarHeight.dp))
            Text(
                title,
                Modifier
                    .padding(8.dp),
                color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,
            )
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
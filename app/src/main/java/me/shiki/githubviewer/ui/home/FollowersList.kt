package me.shiki.githubviewer.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import me.shiki.githubviewer.model.Follower
import me.shiki.githubviewer.vm.HomeViewModel

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Composable
fun FollowersList(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val lazyFollowers: LazyPagingItems<Follower> = viewModel.followers.collectAsLazyPagingItems()
}
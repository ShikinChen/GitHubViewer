package me.shiki.githubviewer.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import me.shiki.githubviewer.ext.visible
import timber.log.Timber

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Composable
fun <T : Any> CommonList(
    modifier: Modifier = Modifier,
    models: LazyPagingItems<T>,
    state: SwipeRefreshState,
    content: @Composable (T) -> Unit
) {
    if (models.itemCount != 0) {
        SwipeRefresh(
            state = state,
            onRefresh = { models.refresh() },
            indicator = { st, tr ->
                SwipeRefreshIndicator(
                    state = st,
                    refreshTriggerDistance = tr,
                    contentColor = MaterialTheme.colors.primary
                )
            },
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .visible(models.loadState.refresh !is LoadState.Loading)
            ) {
                items(models) {
                    it?.let {
                        content.invoke(it)
                    }
                }
                models.apply {
                    when {
                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val error = models.loadState.refresh as LoadState.Error
                            item {
                                Timber.e("Refresh error: $error.error.localizedMessage")
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            if (models.loadState.refresh is LoadState.Error) {
                                val error = models.loadState.refresh as LoadState.Error
                                item {
                                    Timber.e("Append error: $error.error.localizedMessage")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    CommonLoading(visibility = models.loadState.refresh is LoadState.Loading)
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}
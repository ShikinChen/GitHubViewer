package me.shiki.githubviewer.ui.home

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import me.shiki.githubviewer.model.Follower
import me.shiki.githubviewer.ui.common.CommonList
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
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
    val models: LazyPagingItems<Follower> = viewModel.followers.collectAsLazyPagingItems()
    CommonList(
        modifier,
        models = models,
        state = rememberSwipeRefreshState(models.loadState.refresh is LoadState.Loading)
    ) {
        FollowerItem(it)
    }
}

@Composable
fun FollowerItem(model: Follower, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                CustomTabsIntent
                    .Builder()
                    .build()
                    .launchUrl(context, Uri.parse("https://github.com/${model.login}"))
            }, elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(modifier = modifier.padding(8.dp)) {
            val (image, body) = createRefs()
            Image(
                painter = rememberImagePainter(model.avatarUrl),
                contentDescription = null,
                modifier = modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
            ConstraintLayout(modifier = modifier
                .padding(start = 6.dp)
                .constrainAs(body) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }) {
                val (name, type) = createRefs()
                Text(
                    text = model.login,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                        .constrainAs(name) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(type.top)
                        }
                )
                Text(
                    text = model.type,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                        .constrainAs(type) {
                            top.linkTo(name.bottom)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemFollowerPreview() {
    GitHubViewerTheme {
        FollowerItem(model = Follower.mock())
    }
}
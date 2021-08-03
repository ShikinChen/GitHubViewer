package me.shiki.githubviewer.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import me.shiki.githubviewer.base.ConstsDateFormat
import me.shiki.githubviewer.model.Repo
import me.shiki.githubviewer.ui.common.CommonList
import me.shiki.githubviewer.ui.common.LanguageImage
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
import java.time.format.DateTimeFormatter

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Composable
fun ReposList(
    modifier: Modifier = Modifier,
    models: LazyPagingItems<Repo>,
    navigateToDetailsRepo: (Long) -> Unit
) {
    CommonList(modifier, models, rememberSwipeRefreshState(models.loadState.refresh is LoadState.Loading)) {
        ItemRepo(model = it, navigateToRepoView = navigateToDetailsRepo)
    }
}

@Composable
fun ItemRepo(
    model: Repo,
    modifier: Modifier = Modifier,
    navigateToRepoView: (Long) -> Unit = { },
) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                navigateToRepoView(model.id)
            }, elevation = 8.dp, shape = RoundedCornerShape(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            LanguageImage(
                language = model.language,
                Modifier
                    .size(56.dp, 56.dp)
                    .clip(
                        RoundedCornerShape(
                            4.dp
                        )
                    )
                    .background(Color.White)
            )
            Column {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                )
                Text(
                    text = "Created: ${
                        model.createdAt.toInstant()
                            .toLocalDateTime(TimeZone.currentSystemDefault())
                            .toJavaLocalDateTime()
                            .format(DateTimeFormatter.ofPattern(ConstsDateFormat.DATE_TIME_LIST))
                    }",
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemRepoPreview() {
    GitHubViewerTheme {
        ItemRepo(model = Repo.mock())
    }
}
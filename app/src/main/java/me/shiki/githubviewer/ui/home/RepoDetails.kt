package me.shiki.githubviewer.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import me.shiki.githubviewer.base.ConstsDateFormat
import me.shiki.githubviewer.model.Repo
import me.shiki.githubviewer.ui.common.AppBar
import me.shiki.githubviewer.ui.common.CommonLoading
import me.shiki.githubviewer.ui.common.LanguageImage
import me.shiki.githubviewer.vm.RepoDetailsViewModel
import java.time.format.DateTimeFormatter
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@ExperimentalTime
@ExperimentalCoroutinesApi
@Composable
fun RepoDetails(
    repoId: Long,
    viewModel: RepoDetailsViewModel,
) {
    val model by viewModel.findByIdRepo(repoId).collectAsState(initial = null)
    model?.let {
        RepoView(it)
    }
    CommonLoading(model == null)
}

@ExperimentalTime
@Composable
fun RepoView(model: Repo) {
    Scaffold(topBar = { AppBar(title = "Repo: ${model.name}") }) {
        val modifier = Modifier.padding(it)
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.background)
                .fillMaxHeight()
        ) {
            LanguageImage(
                language = model.language,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White)
            )
            Text(
                text = "Name: ${if (model.name.isBlank()) "Empty" else model.name}",
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
            )
            Text(
                text = "Language: ${if (model.language.isBlank()) "Empty" else model.language}",
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
            )
            Text(
                text = "Created: ${
                    model.createdAt.toInstant()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                        .toJavaLocalDateTime()
                        .format(DateTimeFormatter.ofPattern(ConstsDateFormat.DATE_TIME_DETAILS))
                }",
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
            )
            Text(
                text = (Clock.System.now() - model.createdAt.toInstant()).toInt(DurationUnit.SECONDS).let {
                    return@let "Duration: ~${
                        listOf(
                            ":%02d".format(it % 60),
                            ":%02d".format((it % 3600) / 60),
                            "%d".format((it % 86400) / 3600),
                            "%d days ".format((it % 31536000) / 86400),
                            "%d years ".format(it / 31536000)
                        )
                            .filter { t -> !t.contains("0 days") && !t.contains("0 years") }
                            .reversed()
                            .joinToString("") { t ->
                                when (t) {
                                    "1 days" -> "1 day"
                                    "1 years" -> "1 year"
                                    else -> t
                                }
                            }
                    }"
                },
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
            )
        }
    }
}
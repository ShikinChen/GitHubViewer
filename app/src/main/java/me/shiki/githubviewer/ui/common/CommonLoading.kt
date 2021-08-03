package me.shiki.githubviewer.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.shiki.githubviewer.ext.visible

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Composable
fun CommonLoading(visibility: Boolean) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .visible(visibility)
    ) {
        val (loading) = createRefs()
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp, start = 8.dp, end = 4.dp)
                .constrainAs(loading) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}
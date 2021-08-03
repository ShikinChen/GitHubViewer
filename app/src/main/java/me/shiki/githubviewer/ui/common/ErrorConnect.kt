package me.shiki.githubviewer.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiBad
import androidx.compose.material.icons.filled.SingleBed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.shiki.githubviewer.R
import me.shiki.githubviewer.ui.theme.BlackLight
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
import me.shiki.githubviewer.ui.theme.Purple700
import me.shiki.githubviewer.ui.theme.Red100

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Composable
fun ErrorConnect(repeat: () -> Unit) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(BlackLight)
    ) {
        val (constraintLayout) = createRefs()
        ConstraintLayout(
            Modifier
                .background(Color.Transparent)
                .constrainAs(constraintLayout) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
                Icon(
                    imageVector = Icons.Filled.SignalWifiBad,
                    contentDescription = null,
                    tint = Red100,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = stringResource(id = R.string.no_connection),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(30.dp)
                )
                Button(onClick = repeat, colors = ButtonDefaults.textButtonColors(backgroundColor = Color.White)) {
                    Text(text = stringResource(id = R.string.no_connection_button), color = Purple700)
                }
            }
        }

    }
}

@Preview
@Composable
fun ErrorConnectPreview() {
    GitHubViewerTheme {
        ErrorConnect {}
    }
}
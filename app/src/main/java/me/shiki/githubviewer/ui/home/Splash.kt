package me.shiki.githubviewer.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.shiki.githubviewer.R
import me.shiki.githubviewer.ui.theme.BlackLight
import me.shiki.githubviewer.ui.theme.Blue50_30
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Composable
fun Splash() {
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
            Column(
                Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.mipmap.ic_launcher),
                    contentDescription = null,
                    Modifier.width(200.dp),
                    contentScale = ContentScale.Crop,
                )
                CircularProgressIndicator(color = Blue50_30, modifier = Modifier.width(24.dp))
            }
        }
    }
}

@Preview
@Composable
fun SplashPreviewLight() {
    GitHubViewerTheme {
        Splash()
    }
}
package me.shiki.githubviewer.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import me.shiki.githubviewer.ui.common.AppBar
import me.shiki.githubviewer.vm.RepoDetailsViewModel

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Composable
fun RepoDetails(
    repoId: Long,
    viewModel: RepoDetailsViewModel,
    upPress: () -> Unit
) {
    Scaffold(topBar = { AppBar(title = "Repo") }) {

    }
}
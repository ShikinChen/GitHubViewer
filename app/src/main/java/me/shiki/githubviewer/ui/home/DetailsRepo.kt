package me.shiki.githubviewer.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import me.shiki.githubviewer.vm.HomeViewModel

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Composable
fun DetailsRepo(
    repoId: Long,
    viewModel: HomeViewModel,
    upPress: () -> Unit
) {
    Text(text = "DetailsRepo")
}
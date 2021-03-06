package me.shiki.githubviewer.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
fun Modifier.visible(visibility: Boolean): Modifier = this.then(alpha(if (visibility) 1f else 0f))
package me.shiki.githubviewer.ext

import androidx.compose.ui.graphics.Color

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */

fun String.parseColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}
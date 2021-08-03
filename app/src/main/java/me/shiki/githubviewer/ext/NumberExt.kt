package me.shiki.githubviewer.ext

import android.content.res.Resources
import androidx.compose.ui.graphics.Color

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */

fun Number.px2dp(): Float {
    val scale = Resources.getSystem().displayMetrics.density
    return this.toFloat() / scale + 0.5f
}
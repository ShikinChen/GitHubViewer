package me.shiki.githubviewer.base

import androidx.compose.runtime.staticCompositionLocalOf
import me.shiki.githubviewer.vm.MainViewModel

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
val LocalBaseViewModel = staticCompositionLocalOf<MainViewModel> {
    error("No MainViewModel found!")
}
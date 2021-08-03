package me.shiki.githubviewer.data.repos.impl

import androidx.compose.runtime.Immutable

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Immutable
class ResponseRepo(
    val id: Long,
    val name: String,
    val language: String? = null,
    val created_at: String
)
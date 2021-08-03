package me.shiki.githubviewer.data.follower.impl

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
data class ResponseFollower(
    val id: Long,
    val login: String,
    val type: String,
    val avatar_url: String
)
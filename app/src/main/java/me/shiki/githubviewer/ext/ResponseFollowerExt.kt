package me.shiki.githubviewer.ext

import me.shiki.githubviewer.data.follower.impl.ResponseFollower
import me.shiki.githubviewer.model.Follower

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */

fun ResponseFollower.toModelFollower(): Follower {
    return Follower(
        id = this.id,
        login = this.login,
        type = this.type,
        avatarUrl = this.avatar_url
    )
}

fun List<ResponseFollower>.toFollowers(): List<Follower> {
    return this.map { it.toModelFollower() }
}
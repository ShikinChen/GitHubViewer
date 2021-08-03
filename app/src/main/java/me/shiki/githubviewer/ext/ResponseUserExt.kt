package me.shiki.githubviewer.ext

import me.shiki.githubviewer.data.user.impl.ResponseUser
import me.shiki.githubviewer.model.User

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */

fun ResponseUser.toUser(): User {
    return User(
        id = this.id,
        login = this.login,
        avatarUrl = this.avatarUrl,
        followersUrl = this.followersUrl,
        reposUrl = this.reposUrl,
        name = this.name,
        bio = this.bio ?: "",
        createdAt = this.created_at,
    )
}
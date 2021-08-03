package me.shiki.githubviewer.model

import androidx.compose.runtime.Immutable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Immutable
data class Follower(
    var id: Long,
    var login: String,
    var type: String,
    var avatarUrl: String
) {
    companion object {
        fun mock() = Follower(
            id = 1,
            login = "keygenqt",
            type = "User",
            avatarUrl = "https://avatars.githubusercontent.com/u/9665914?v=4",
        )
    }
}
package me.shiki.githubviewer.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import io.objectbox.annotation.Id
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Immutable
@Serializable
@Parcelize
data class User(
    @Id var id: Long,
    var login: String,
    var avatarUrl: String,
    var followersUrl: String,
    var reposUrl: String,
    var name: String?,
    var bio: String,
    var createdAt: String
) : Parcelable
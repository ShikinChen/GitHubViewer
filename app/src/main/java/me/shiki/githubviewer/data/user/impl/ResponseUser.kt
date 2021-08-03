package me.shiki.githubviewer.data.user.impl

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Immutable
class ResponseUser(
    var id: Long,
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("followers_url")
    var followersUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    var name: String,
    var bio: String? = null,
    var created_at: String
)
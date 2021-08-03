package me.shiki.githubviewer.ext

import androidx.compose.ui.graphics.Color
import com.tencent.mmkv.MMKV
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.model.User

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
const val KEY_USER = "key_user"
fun MMKV.putUser(user: User) {
    encode(KEY_USER, user)
}

fun MMKV.getUser(): User? {
    return decodeParcelable(KEY_USER, User::class.java)
}

fun MMKV.getLastUpdateRepos(): Long {
    return decodeLong(Consts.LAST_UPDATE_REPOS, 0)
}

fun MMKV.putLastUpdateRepos(lastUpdateRepos: Long) {
    encode(Consts.LAST_UPDATE_REPOS, lastUpdateRepos)
}
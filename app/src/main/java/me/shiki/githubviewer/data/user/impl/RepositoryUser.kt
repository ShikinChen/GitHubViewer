package me.shiki.githubviewer.data.user.impl

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.flow
import me.shiki.githubviewer.base.NotFoundException
import me.shiki.githubviewer.base.ResponseResult
import me.shiki.githubviewer.data.user.UserService
import me.shiki.githubviewer.ext.toUser
import javax.inject.Inject

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
class RepositoryUser @Inject constructor(
    private val service: UserService,
    private val gitHubUser: String
) {
    @WorkerThread
    fun loadingUser() = flow {
        try {
            service.getUser(gitHubUser).body()?.toUser()?.let {
                emit(ResponseResult.Success(it))
            } ?: run {
                throw NotFoundException()
            }
        } catch (e: Exception) {
            emit(ResponseResult.Error(e))
        }
    }
}
package me.shiki.githubviewer.data.follower.impl

import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import me.shiki.githubviewer.base.ResponseResult
import me.shiki.githubviewer.base.executeWithResponse
import me.shiki.githubviewer.data.follower.FollowerService
import me.shiki.githubviewer.ext.getUser
import me.shiki.githubviewer.model.Follower
import javax.inject.Inject
import javax.inject.Singleton
import me.shiki.githubviewer.ext.toFollowers

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Singleton
class FollowerRepository @Inject constructor(
    private val mmkv: MMKV,
    private val service: FollowerService
) {
    suspend fun getModels(page: Int): ResponseResult<List<Follower>> {
        return withContext(Dispatchers.IO) {
            delay(1000)
            executeWithResponse {
                service.listFollowers(mmkv.getUser()!!.followersUrl, page)
                    .body()
                    ?.toFollowers()
                    ?: emptyList()
            }
        }
    }
}
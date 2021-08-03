package me.shiki.githubviewer.data.follower

import androidx.annotation.IntRange
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.data.follower.impl.ResponseFollower
import me.shiki.githubviewer.data.repos.impl.ResponseRepo
import me.shiki.githubviewer.model.Follower
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
interface FollowerService {
    @GET
    suspend fun listFollowers(
        @Url reposUrl: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 1,
            to = Consts.MAX_PAGE_SIZE.toLong()
        ) perPage: Int = Consts.PER_PAGE,
        @Query("sort") sort: String = "created"
    ): Response<List<ResponseFollower>>
}
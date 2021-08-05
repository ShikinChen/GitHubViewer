package me.shiki.githubviewer.data.repos

import androidx.annotation.IntRange
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.data.repos.impl.ResponseRepo
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
interface RepoService {
    @GET
    suspend fun listRepo(
        @Url reposUrl: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 1,
            to = Consts.MAX_PAGE_SIZE.toLong()
        ) perPage: Int = Consts.PAGE_SZIE,
        @Query("sort") sort: String = "created"
    ): Response<List<ResponseRepo>>
}
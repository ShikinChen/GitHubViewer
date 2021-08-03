package me.shiki.githubviewer.data.user

import me.shiki.githubviewer.data.user.impl.ResponseUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
interface UserService {
    @GET("/users/{login}")
    suspend fun getUser(@Path("login") login: String): Response<ResponseUser>
}
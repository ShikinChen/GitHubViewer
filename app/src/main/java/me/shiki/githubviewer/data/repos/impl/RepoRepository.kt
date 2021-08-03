package me.shiki.githubviewer.data.repos.impl

import com.tencent.mmkv.MMKV
import kotlinx.coroutines.delay
import me.shiki.githubviewer.base.NotFoundException
import me.shiki.githubviewer.data.repos.RepoService
import me.shiki.githubviewer.ext.getUser
import me.shiki.githubviewer.ext.toRepos
import me.shiki.githubviewer.model.Repo
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Singleton
class RepoRepository @Inject constructor(
    private val mmkv: MMKV,
    private val service: RepoService
) {
    suspend fun getModels(page: Int): List<Repo> {
        delay(1000)
        return service.listRepo(mmkv.getUser()!!.reposUrl, page).body()?.toRepos(page) ?: throw NotFoundException()
    }
}
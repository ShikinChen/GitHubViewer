package me.shiki.githubviewer.data.repos.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.tencent.mmkv.MMKV
import dagger.hilt.android.scopes.ViewModelScoped
import me.shiki.githubviewer.dao.RepoDao
import me.shiki.githubviewer.data.repos.impl.RepoRepository
import me.shiki.githubviewer.ext.getLastUpdateRepos
import me.shiki.githubviewer.ext.putLastUpdateRepos
import me.shiki.githubviewer.model.Repo
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@ExperimentalPagingApi
@ViewModelScoped
class RemoteMediatorRepo @Inject constructor(
    private val mmkv: MMKV,
    private val repoDao: RepoDao,
    private val repoRepository: RepoRepository
) :
    RemoteMediator<Int, Repo>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.HOURS.toMillis(1)
        return if (System.currentTimeMillis() - mmkv.getLastUpdateRepos() >= cacheTimeout) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Repo>): MediatorResult {
        return try {
            val loadPage = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()?.page?.plus(1)
                }
            }

            val response = repoRepository.getModels(page = loadPage ?: 1)

            if (loadType == LoadType.REFRESH) {
                mmkv.putLastUpdateRepos(System.currentTimeMillis())
                repoDao.clearAll()
            }

            Timber.d("response.size:${response.size}")
            repoDao.insertAll(response)

            MediatorResult.Success(
                endOfPaginationReached = response.isEmpty()
            )
        } catch (e: Exception) {
            Timber.e(e)
            MediatorResult.Error(e)
        }
    }
}
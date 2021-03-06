package me.shiki.githubviewer.vm

import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tencent.mmkv.MMKV
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.base.NotFoundException
import me.shiki.githubviewer.base.ResponseResult
import me.shiki.githubviewer.dao.RepoDao
import me.shiki.githubviewer.data.follower.impl.FollowerRepository
import me.shiki.githubviewer.data.follower.paging.FollowerPageSource
import me.shiki.githubviewer.data.repos.paging.RemoteMediatorRepo
import me.shiki.githubviewer.data.user.impl.RepositoryUser
import me.shiki.githubviewer.ext.putUser
import me.shiki.githubviewer.ext.success
import me.shiki.githubviewer.model.Follower
import me.shiki.githubviewer.model.Repo
import me.shiki.githubviewer.model.User
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@HiltViewModel
@ExperimentalCoroutinesApi
class HomeViewModel @ExperimentalPagingApi
@Inject constructor(
    private val mmkv: MMKV,
    private val repositoryUser: RepositoryUser,
    private val remoteMediatorRepo: RemoteMediatorRepo,
    private val followerRepository: FollowerRepository,
    private val repoDao: RepoDao
) :
    ViewModel() {
    init {
        Timber.d("HomeViewModel")
    }

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    @ExperimentalPagingApi
    val repos: Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(pageSize = Consts.PAGE_SZIE),
        remoteMediator = remoteMediatorRepo,
    ) {
        repoDao.createPagingSource()
    }.flow.cachedIn(viewModelScope)

    val followers: Flow<PagingData<Follower>> = Pager(PagingConfig(pageSize = Consts.PAGE_SZIE)) {
        FollowerPageSource(followerRepository)
    }.flow.cachedIn(viewModelScope)

    private val _loadingUser: MutableStateFlow<ResponseResult<User>?> by lazy {
        MutableStateFlow(null)
    }

    val loadingUser = _loadingUser.asStateFlow()

    init {
        repeatLoadingUser()
    }

    fun repeatLoadingUser() {
        _loadingUser.value = null
        repositoryUser.loadingUser().onEach {
            it.success {
                mmkv.putUser(it)
            }
            _loadingUser.value = it
        }.launchIn(viewModelScope)
    }

    @MainThread
    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}
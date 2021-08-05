package me.shiki.githubviewer.vm

import androidx.annotation.MainThread
import androidx.annotation.StringRes
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.base.ResponseResult
import me.shiki.githubviewer.dao.RepoDao
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
class RepoDetailsViewModel @ExperimentalPagingApi
@Inject constructor(
    private val mmkv: MMKV,
    private val repositoryUser: RepositoryUser,
    private val remoteMediatorRepo: RemoteMediatorRepo,
    private val repoDao: RepoDao
) :
    ViewModel() {
}
package me.shiki.githubviewer.data.follower.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.shiki.githubviewer.base.Consts
import me.shiki.githubviewer.data.follower.impl.FollowerRepository
import me.shiki.githubviewer.ext.pagingSucceeded
import me.shiki.githubviewer.model.Follower

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
class FollowerPageSource(private val followerRepository: FollowerRepository) :
    PagingSource<Int, Follower>() {
    override fun getRefreshKey(state: PagingState<Int, Follower>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Follower> {
        val page = params.key ?: 1
        return followerRepository.getModels(page).pagingSucceeded { data ->
            val nextKey = if (data.isEmpty() || data.size < params.loadSize) {
                null
            } else {
                page.plus(1)
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        }
    }
}
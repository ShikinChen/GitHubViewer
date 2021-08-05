package me.shiki.githubviewer.dao

import android.provider.ContactsContract
import androidx.paging.PagingSource
import io.objectbox.Box
import io.objectbox.android.ObjectBoxDataSource
import io.objectbox.query.Query
import me.shiki.githubviewer.model.Repo
import me.shiki.githubviewer.model.Repo_
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.withLock

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Singleton
class RepoDao @Inject constructor(private val box: Box<Repo>) {

    fun insertAll(repos: List<Repo>) {
        box.put(repos)
    }

    fun findQuery(): Query<Repo> {
        return box.query().orderDesc(Repo_.createdAt).build()
    }

    fun findById(repoId: Long): Repo? {
        return box.query().equal(Repo_.repoId, repoId).build().findFirst()
    }

    fun clearAll() {
        box.removeAll()
    }

    fun createPagingSource(): PagingSource<Int, Repo> {
        return ObjectBoxDataSource.Factory(findQuery()).asPagingSourceFactory().invoke()
    }

    fun runInTx(runnable: Runnable) {
        box.store.runInTx(runnable)
    }
}
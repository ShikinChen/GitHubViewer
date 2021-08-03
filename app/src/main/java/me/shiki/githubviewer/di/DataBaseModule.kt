package me.shiki.githubviewer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import me.shiki.githubviewer.BuildConfig
import me.shiki.githubviewer.model.Repo
import me.shiki.githubviewer.model.MyObjectBox
import timber.log.Timber
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideBoxStore(@ApplicationContext context: Context): BoxStore {
        val boxStore = MyObjectBox.builder().androidContext(context).build()
        if (BuildConfig.DEBUG) {
            val started = AndroidObjectBrowser(boxStore).start(context)
            Timber.d("Started: $started")
        }
        return boxStore
    }

    @Provides
    @Singleton
    fun provideRepoBox(boxStore: BoxStore): Box<Repo> {
        return boxStore.boxFor(Repo::class.java)
    }
}
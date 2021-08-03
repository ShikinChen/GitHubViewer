package me.shiki.githubviewer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.shiki.githubviewer.R
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    @ApiBaseUrl
    fun provideApiBaseUrl(@ApplicationContext context: Context): String {
        return context.getString(R.string.api_base_url)
    }
}
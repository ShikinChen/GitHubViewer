package me.shiki.githubviewer.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import me.shiki.githubviewer.R
import me.shiki.githubviewer.data.user.UserService
import me.shiki.githubviewer.data.user.impl.RepositoryUser

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepositoryUser(
        @ApplicationContext context: Context,
        service: UserService
    ): RepositoryUser {
        return RepositoryUser(service, context.getString(R.string.github_user))
    }
}
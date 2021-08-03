package me.shiki.githubviewer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.shiki.githubviewer.data.follower.FollowerService
import me.shiki.githubviewer.data.repos.RepoService
import me.shiki.githubviewer.data.user.UserService
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {
    @Provides
    @Singleton
    fun provideServiceUser(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): RepoService {
        return retrofit.create(RepoService::class.java)
    }

    @Provides
    @Singleton
    fun provideFollowerService(retrofit: Retrofit): FollowerService {
        return retrofit.create(FollowerService::class.java)
    }
}
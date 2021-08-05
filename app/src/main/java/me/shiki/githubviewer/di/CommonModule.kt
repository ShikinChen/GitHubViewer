package me.shiki.githubviewer.di

import android.content.Context
import coil.ImageLoader
import com.tencent.mmkv.MMKV
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    @Singleton
    fun provideMMKV(@ApplicationContext context: Context): MMKV {
        MMKV.initialize(context)
        return MMKV.defaultMMKV()
    }

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context, okHttpClient: OkHttpClient): ImageLoader {
        return ImageLoader.Builder(context).okHttpClient(okHttpClient)
            .crossfade(true)
            .build()
    }
}
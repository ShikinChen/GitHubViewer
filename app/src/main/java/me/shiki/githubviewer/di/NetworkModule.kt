package me.shiki.githubviewer.di

import com.safframework.http.interceptor.AndroidLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.shiki.githubviewer.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                AndroidLoggingInterceptor.build(isDebug = BuildConfig.DEBUG, hideVerticalLine = true)
            )
            .addInterceptor {
                val original = it.request()
                val request = original.newBuilder().apply {
                    BuildConfig.GITHUB_TOKEN.let { token -> header("Authorization", "token $token") }
                }
                    .method(original.method, original.body)
                    .build()
                it.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @ApiBaseUrl apiBaseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
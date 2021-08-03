package me.shiki.githubviewer.di

import android.app.Activity
import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gyf.immersionbar.ImmersionBar
import com.tencent.mmkv.MMKV
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import me.shiki.githubviewer.R
import me.shiki.githubviewer.data.user.UserService
import me.shiki.githubviewer.data.user.impl.RepositoryUser
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
}

@Module
@InstallIn(ActivityComponent::class)
object ViewModelCommonModule {
    @Provides
    @StatusBarHeight
    @Singleton
    fun provideStatusBarHeight(
        @ActivityContext activity: Activity,
    ): Int {
        return ImmersionBar.getStatusBarHeight(activity)
    }
}
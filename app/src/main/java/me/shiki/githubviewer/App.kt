package me.shiki.githubviewer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import me.jessyan.autosize.AutoSizeConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        AutoSizeConfig.getInstance().apply {
            isCustomFragment = true
            setLog(BuildConfig.DEBUG)
            privateFontScale = 1.0f
        }
    }
}
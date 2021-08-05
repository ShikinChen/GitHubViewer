package me.shiki.githubviewer.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import dagger.hilt.android.AndroidEntryPoint
import me.shiki.githubviewer.R
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme

abstract class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //取消全屏
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)

        initImmersionBar()

        setContent {
            content()
        }
    }

    @Composable
    protected abstract fun content()

    abstract val navController: NavHostController

    private fun initImmersionBar() {
        ImmersionBar.with(this)
            .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            .keyboardEnable(true)
            .statusBarDarkFont(true).init()
    }
}


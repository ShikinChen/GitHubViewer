package me.shiki.githubviewer.ui.common

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.gyf.immersionbar.ImmersionBar
import me.shiki.githubviewer.R
import me.shiki.githubviewer.ext.px2dp
import me.shiki.githubviewer.ui.BaseActivity
import me.shiki.githubviewer.ui.main.MainActivity
import me.shiki.githubviewer.ui.theme.Purple700

/**
 *
 * @author shiki
 * @date 2021/8/3
 *
 */
@Composable
fun AppBar(title: String, isShowNavigationIcon: Boolean = true) {
    val activity = LocalContext.current as Activity
    val statusBarHeight = ImmersionBar.getStatusBarHeight(activity).px2dp()
    val actionBarHeight = ImmersionBar.getActionBarHeight(activity).px2dp()

    TopAppBar(
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.statusBarsHeight())
                Text(
                    title,
                    Modifier
                        .padding(8.dp),
                    color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,
                )
            }
        },
        elevation = 6.dp,
        backgroundColor = Purple700,
        modifier = Modifier
            .navigationBarsHeight((actionBarHeight + statusBarHeight).dp),
        navigationIcon = if (isShowNavigationIcon) {
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.statusBarsHeight())
                    IconButton(onClick = {
                        if (activity is BaseActivity) {
                            activity.navController.navigateUp()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIos,
                            contentDescription = stringResource(R.string.cd_navigate_up)
                        )
                    }
                }
            }
        } else {
            null
        }
    )
}
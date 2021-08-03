package me.shiki.githubviewer.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.shiki.githubviewer.R
import me.shiki.githubviewer.base.ConstsLanguage
import me.shiki.githubviewer.ui.theme.GitHubViewerTheme
import java.util.Locale

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */
@Composable
fun LanguageImage(
    language: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding(4.dp), elevation = 6.dp, shape = RoundedCornerShape(6.dp)
    ) {
        Image(
            painter = painterResource(
                id = when (language.lowercase(Locale.getDefault())) {
                    ConstsLanguage.LANGUAGE_BASH -> R.drawable.ic_pl_bash_plain
                    ConstsLanguage.LANGUAGE_C -> R.drawable.ic_pl_c_plain
                    ConstsLanguage.LANGUAGE_CPLUSPLUS -> R.drawable.ic_pl_cplusplus_plain
                    ConstsLanguage.LANGUAGE_DART -> R.drawable.ic_pl_dart_plain
                    ConstsLanguage.LANGUAGE_ELIXIR -> R.drawable.ic_pl_elixir_plain
                    ConstsLanguage.LANGUAGE_ERLANG -> R.drawable.ic_pl_erlang_plain
                    ConstsLanguage.LANGUAGE_GROOVY -> R.drawable.ic_pl_groovy_plain
                    ConstsLanguage.LANGUAGE_HASKELL -> R.drawable.ic_pl_haskell_plain
                    ConstsLanguage.LANGUAGE_JAVA -> R.drawable.ic_pl_java_plain
                    ConstsLanguage.LANGUAGE_JAVASCRIPT -> R.drawable.ic_pl_javascript_plain
                    ConstsLanguage.LANGUAGE_KOTLIN -> R.drawable.ic_pl_kotlin_plain
                    ConstsLanguage.LANGUAGE_PHP -> R.drawable.ic_pl_php_plain
                    ConstsLanguage.LANGUAGE_PYTHON -> R.drawable.ic_pl_python_plain
                    ConstsLanguage.LANGUAGE_RUBY -> R.drawable.ic_pl_ruby_plain
                    ConstsLanguage.LANGUAGE_RUST -> R.drawable.ic_pl_rust_plain
                    ConstsLanguage.LANGUAGE_SCALA -> R.drawable.ic_pl_scala_plain
                    else -> R.drawable.ic_github_original
                }
            ), contentDescription = null,
            modifier
                .padding(5.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun LanguageImagePreview() {
    GitHubViewerTheme {
        LanguageImage(language = ConstsLanguage.LANGUAGE_BASH)
    }
}
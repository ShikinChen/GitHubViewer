package me.shiki.githubviewer.model

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
@Immutable
@Entity
data class Repo(
    @Id var id: Long = 0,
    var repoId: Long,
    var name: String,
    var language: String,
    var createdAt: String,
    var page: Int,
) {
    companion object {
        fun mock() = Repo(
            id = 1,
            repoId = 1,
            name = "android-stack_2021",
            language = "Kotlin",
            createdAt = "2021-05-15T19:50:40Z",
            page = 0
        )
    }
}
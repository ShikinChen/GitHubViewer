package me.shiki.githubviewer.ext

import me.shiki.githubviewer.data.repos.impl.ResponseRepo
import me.shiki.githubviewer.model.Repo

/**
 *
 * @author shiki
 * @date 2021/8/2
 *
 */

fun ResponseRepo.toRepo(page: Int): Repo {
    return Repo(
        repoId = this.id,
        name = this.name,
        language = this.language ?: "",
        createdAt = this.created_at,
        page = page,
    )
}

fun List<ResponseRepo>.toRepos(page: Int): List<Repo> {
    return this.map { it.toRepo(page) }
}
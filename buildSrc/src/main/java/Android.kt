
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

/**
 *
 * @author shiki
 * @date 2021/7/29
 *
 */
object Android {
    const val buildTools = "30.0.3"
    const val minSdk = 26
    const val targetSdk = 30
    const val compileSdk = 30

    fun addRepos(handler: RepositoryHandler) {
        handler.google()
        handler.mavenCentral()
        handler.maven("https://oss.sonatype.org/content/repositories/snapshots")
        handler.maven("https://jitpack.io")
        handler.maven("https://repository.mulesoft.org/nexus/content/repositories/public")
        // handler.maven("https://dl.bintray.com/zchu/maven/")
        // handler.maven("http://maven.aliyun.com/nexus/content/groups/public")
        // handler.maven("http://mvn.mob.com/android")
        // handler.maven(
        //     "http://repo.baichuan-android.taobao.com/content/groups/BaichuanRepositories/"
        // )
        // handler.maven("https://dl.bintray.com/rongcloud/maven")
        // handler.maven(
        //     "http://developer.huawei.com/repo"
        // )
        // handler.maven(
        //     "https://plugins.gradle.org/m2/"
        // )
    }
}
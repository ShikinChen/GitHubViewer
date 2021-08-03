package dependencies

/**
 *
 * @author shiki
 * @date 2021/7/30
 *
 */
object ObjectBox {
    const val plugin = "io.objectbox:objectbox-gradle-plugin:${Versions.objectBox}"
    val objectBoxBrowser = "io.objectbox:objectbox-android-objectbrowser:${Versions.objectBox}"
    val android = "io.objectbox:objectbox-android:${Versions.objectBox}"
}
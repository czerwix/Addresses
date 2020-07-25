@file:Suppress("MayBeConstant")

object ApplicationId {
    val id = "com.mobeedev.addresses"
}

object Modules {
    val app = ":app"

    val commonDb = ":common-db"
    val commonUi = ":common-ui"
    val commonDomain = ":common-domain"
}

object EmployeesModules {
    val data = ":feature-employees-data"
    val domain = ":feature-employees-domain"
    val ui =":feature-employees-ui"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0.0"
}

object Versions {
    val gradle = "3.6.3"

    val compileSdk = 28
    val minSdk = 24
    val targetSdk = 28

    val appcompat = "1.0.2"
    val design = "1.2.0-beta01"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val constraint = "1.1.3"
    val navigation = "2.1.0-rc01"

    val ktx = "1.0.1"

    val kotlin = "1.3.72"
    val kotlinCoroutines = "1.3.7"
    val kotlinCoroutinesTest = "1.3.6"

    val timber = "4.7.1"
    val lifecycle = "2.2.0"
    val arch = "2.1.0"
    val leakCanary = "2.0"
    val koin = "2.1.5"
    val flipper = "0.34.0"
    val soLoader = "0.8.2"

    val junit = "4.12"
    val assertjCore = "3.12.2"
    val mockk = "1.9.3"
    val threetenabp = "1.4.0"

    val sqldelight = "1.4.0"

    val epoxy = "3.11.0"
    val mvrx = "1.4.0"

    val threeTen = "1.2.1"

    val viewPager2 = "1.0.0-rc01"

    val swipeRefreshLayout = "1.0.0"

    val espressoCore = "3.1.0"
    val espressoIntents = "3.1.0"
    val espressoContrib = "2.2"
    val androidxCore = "1.1.0"
    val androidxRules = "1.1.0"
    val fragmentTesting = "1.1.0"
    val runner = "1.1.0"
    val extJUnit = "1.1.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val koin = "org.koin:koin-core:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinScopes = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinAndroidxExperimental = "org.koin:koin-androidx-ext:${Versions.koin}"

    val sqldelightAndroidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"

    val mvrx = "com.airbnb.android:mvrx:${Versions.mvrx}"
    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"

    val threeTen = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTen}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    val swipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object DebugLibraries {
    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    val soLoaderForFlipper = "com.facebook.soloader:soloader:${Versions.soLoader}"
    val flipper = "com.facebook.flipper:flipper:${Versions.flipper}"
    val leakCanaryPluginForFlipper =
        "com.facebook.flipper:flipper-leakcanary-plugin:${Versions.flipper}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    val threetenabp = "org.threeten:threetenbp:${Versions.threetenabp}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.arch}"
    val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    val espressoContrib =
        "com.android.support.test.espresso:espresso-contrib:${Versions.espressoContrib}"
    val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutinesTest}"

    val androidxCore = "androidx.test:core:${Versions.androidxCore}"
    val androidxRules = "androidx.test:rules:${Versions.androidxRules}"
    val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    val runner = "androidx.test:runner:${Versions.runner}"
    val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"

    val sqldelightSqliteDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqldelight}"

    val mvrx = "com.airbnb.android:mvrx-testing:${Versions.mvrx}"
    val koin = "org.koin:koin-test:${Versions.koin}"
}

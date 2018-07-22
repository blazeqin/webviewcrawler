object Versions {
    val support_lib = "28.0.0-alpha3"
    val kotlin_version = "1.2.51"
    val minSdkVersion =  21
    val targetSdkVersion = 28
    val versionCode = 1
    val versionName = "1.0"
    val compileSdkVersion = 28

    val constraint_layout = "1.1.2"
    val junit_version = "4.12"
    val runner_version = "1.0.1"
    val espresso_version = "3.0.2"
    val paging_version = "1.0.0"
    val paging_rxjava2 = "1.0.0-rc1"
    val lifecycle_version = "1.1.1"
    val room_version = "1.1.1"
    val xrecyclerview_version = "1.5.9"
    val retrofit2_version = "2.3.0"
    val retrofit2_gson = "2.3.0"
    val glide_version = "4.4.0"
    val multidex_version = "1.0.3"
    val work_version = "1.0.0-alpha01"
    val navigation = "1.0.0-alpha02"
    val core_version = "0.3"
    val gson_version = "2.8.2"
    val kotlin_jdk8 = "1.2.51"
    val andpermission = "2.0.0-rc11"
}

object Libs {
    val support_appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    val kotlin_stdlib_jre7 = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.kotlin_version}"
    val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"
    val junit = "junit:junit:${Versions.junit_version}"
    val test_runner =  "com.android.support.test:runner:${Versions.runner_version}"
    val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso_version}"
    val paging = "android.arch.paging:runtime:${Versions.paging_version}"
    val paging_common = "android.arch.paging:common:${Versions.paging_version}"
    val paging_rxjava2 = "android.arch.paging:rxjava2:${Versions.paging_rxjava2}"
    val lifecycle_extensions = "android.arch.lifecycle:extensions:${Versions.lifecycle_version}"
    val room = "android.arch.persistence.room:runtime:${Versions.room_version}"
    val room_rxjava2 = "android.arch.persistence.room:rxjava2:${Versions.room_version}"
    val room_guava = "android.arch.persistence.room:guava:${Versions.room_version}"
    val room_testing = "android.arch.persistence.room:testing:${Versions.room_version}"
    val room_compiler = "android.arch.persistence.room:compiler:${Versions.room_version}"
    val xrecyclerview = "com.jcodecraeer:xrecyclerview:${Versions.xrecyclerview_version}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit2_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_gson}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"

    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
    val navigation_fragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigation_ui = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"
    val core = "androidx.core:core-ktx:${Versions.core_version}"
    val support_v4 = "com.android.support:support-v4:${Versions.support_lib}"
    val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.support_lib}"
    val gson = "com.google.code.gson:gson:${Versions.gson_version}"
    val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_jdk8}"

    val multidex = "com.android.support:multidex:${Versions.multidex_version}"
    val design = "com.android.support:design:${Versions.support_lib}"
    val andPermission = "com.yanzhenjie:permission:${Versions.andpermission}"

    //workmanager
    val work_runtime = "android.arch.work:work-runtime:${Versions.work_version}"
    val work_runtime_ktx = "android.arch.work:work-runtime-ktx:${Versions.work_version}"
    val work_firebase = "android.arch.work:work-firebase:${Versions.work_version}"
    val work_testing = "android.arch.work:work-testing:${Versions.work_version}"
}
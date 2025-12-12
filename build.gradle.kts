// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Biarkan 2 baris ini pakai alias (bawaan template Android Studio baru)
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Tambahkan plugin Kapt di sini secara manual
    // Gunakan versi yang cukup baru (1.9.20 aman untuk sebagian besar project modern)
    id("org.jetbrains.kotlin.kapt") version "1.9.20" apply false
}
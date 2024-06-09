package dev.agustacandi.learn.pestsentry.utils

import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.di.authNetworkModule
import dev.agustacandi.learn.pestsentry.di.feature.predictModule
import dev.agustacandi.learn.pestsentry.di.feature.authModule
import dev.agustacandi.learn.pestsentry.di.networkModule
import dev.agustacandi.learn.pestsentry.di.newsNetworkModule
import dev.agustacandi.learn.pestsentry.di.predictNetworkModule
import dev.agustacandi.learn.pestsentry.di.preferenceModule
import dev.agustacandi.learn.pestsentry.di.viewModelModule

object ConstVal {

    // Koin Modules
    val koinModules = listOf(authNetworkModule, predictNetworkModule, newsNetworkModule, preferenceModule, viewModelModule, predictModule,authModule)

    // Navigation Bar Destination
    val navBarDestination = listOf(R.id.homeFragment, R.id.analyzeFragment, R.id.profileFragment)

    // Shared Preferences
    const val PREFS_NAME = "pestsentry.pref"
    const val KEY_TOKEN = "key.token"
    const val KEY_NAME = "key.name"
    const val KEY_ONBOARDING_SCREEN = "key.onboarding_screen"

    const val SPLASH_SCREEN_DURATION = 3

    const val MAXIMAL_SIZE = 1000000 // 1 MB
    const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
}
package dev.agustacandi.learn.pestsentry.utils

import dev.agustacandi.learn.pestsentry.R
import dev.agustacandi.learn.pestsentry.di.networkModule
import dev.agustacandi.learn.pestsentry.di.preferenceModule

object ConstVal {
    const val BASE_URL = "https://google.com"

    // Koin Modules
    val koinModules = listOf(networkModule, preferenceModule)

    // Navigation Bar Destination
    val navBarDestination = listOf(R.id.homeFragment, R.id.profileFragment)

    // Shared Preferences
    const val PREFS_NAME = "pestsentry.pref"
    const val KEY_TOKEN = "key.token"
    const val KEY_NAME = "key.name"
    const val KEY_ONBOARDING_SCREEN = "key.onboarding_screen"

    const val SPLASH_SCREEN_DURATION = 3
}
package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferenceManager(get()) }
}
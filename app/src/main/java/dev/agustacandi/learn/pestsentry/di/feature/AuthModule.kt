package dev.agustacandi.learn.pestsentry.di.feature

import dev.agustacandi.learn.pestsentry.data.auth.AuthRepository
import dev.agustacandi.learn.pestsentry.data.auth.AuthRepositoryImpl
import org.koin.dsl.module

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}
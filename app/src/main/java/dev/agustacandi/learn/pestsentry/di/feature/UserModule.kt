package dev.agustacandi.learn.pestsentry.di.feature

import dev.agustacandi.learn.pestsentry.data.user.UserRepository
import dev.agustacandi.learn.pestsentry.data.user.UserRepositoryImpl
import org.koin.dsl.module

val userModule = module {
    single<UserRepository> { UserRepositoryImpl(get(),get()) }
}
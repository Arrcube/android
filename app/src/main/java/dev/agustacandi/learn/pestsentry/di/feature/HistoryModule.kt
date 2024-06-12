package dev.agustacandi.learn.pestsentry.di.feature

import dev.agustacandi.learn.pestsentry.data.auth.AuthRepository
import dev.agustacandi.learn.pestsentry.data.auth.AuthRepositoryImpl
import dev.agustacandi.learn.pestsentry.data.history.repository.HistoryRepository
import dev.agustacandi.learn.pestsentry.data.history.repository.HistoryRepositoryImpl
import org.koin.dsl.module

val historyModule = module {
    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
}
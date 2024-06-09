package dev.agustacandi.learn.pestsentry.di.feature

import dev.agustacandi.learn.pestsentry.data.news.repository.NewsRepository
import dev.agustacandi.learn.pestsentry.data.news.repository.NewsRepositoryImpl
import org.koin.dsl.module

val newsModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }
}
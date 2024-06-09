package dev.agustacandi.learn.pestsentry.di.feature

import dev.agustacandi.learn.pestsentry.data.predict.repository.PredictRepository
import dev.agustacandi.learn.pestsentry.data.predict.repository.PredictRepositoryImpl
import org.koin.dsl.module

val predictModule = module {
    single<PredictRepository> { PredictRepositoryImpl(get()) }
}
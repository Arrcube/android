package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.ui.analyze.AnalyzeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnalyzeViewModel(get()) }
}
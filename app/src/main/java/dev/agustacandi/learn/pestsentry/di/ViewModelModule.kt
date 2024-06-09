package dev.agustacandi.learn.pestsentry.di


import dev.agustacandi.learn.pestsentry.ui.analyze.AnalyzeViewModel
import dev.agustacandi.learn.pestsentry.ui.login.LoginViewModel
import dev.agustacandi.learn.pestsentry.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//all viewmodel must be listed here
val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { AnalyzeViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}
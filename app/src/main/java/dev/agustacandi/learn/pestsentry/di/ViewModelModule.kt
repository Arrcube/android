package dev.agustacandi.learn.pestsentry.di


import dev.agustacandi.learn.pestsentry.ui.analyze.AnalyzeViewModel
import dev.agustacandi.learn.pestsentry.ui.analyze.history.HistoryViewModel
import dev.agustacandi.learn.pestsentry.ui.analyze.result.ResultViewModel
import dev.agustacandi.learn.pestsentry.ui.article.ArticleViewModel
import dev.agustacandi.learn.pestsentry.ui.login.LoginViewModel
import dev.agustacandi.learn.pestsentry.ui.home.HomeViewModel
import dev.agustacandi.learn.pestsentry.ui.profile.ProfileViewModel
import dev.agustacandi.learn.pestsentry.ui.profile.password.PasswordViewModel
import dev.agustacandi.learn.pestsentry.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

//all viewmodel must be listed here
val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { AnalyzeViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { PasswordViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
    viewModel { ResultViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}

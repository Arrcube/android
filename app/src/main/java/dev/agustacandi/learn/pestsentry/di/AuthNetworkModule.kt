package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.auth.AuthService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val authNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single(named("authApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideAuthService(get<Retrofit>(named("authApi"))) }
}

fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)
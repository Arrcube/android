package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.user.UserService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val userNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single(named("userApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideUserService(get<Retrofit>(named("userApi"))) }
}

//fill userService
fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
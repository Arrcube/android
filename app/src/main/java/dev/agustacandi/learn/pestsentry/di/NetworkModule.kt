package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.auth.AuthService
import dev.agustacandi.learn.pestsentry.data.history.service.HistoryService
import dev.agustacandi.learn.pestsentry.data.lib.HeaderInterceptor
import dev.agustacandi.learn.pestsentry.data.news.service.NewsService
import dev.agustacandi.learn.pestsentry.data.predict.service.PredictService
import dev.agustacandi.learn.pestsentry.data.user.UserService
import dev.agustacandi.learn.pestsentry.utils.ConstVal
import dev.agustacandi.learn.pestsentry.utils.PreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loggingInterceptor = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
} else {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(getHeaderInterceptor(get()))
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

    single(named("newsApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single(named("predictApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.PREDICT_PEST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single(named("userApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single { provideAuthService(get<Retrofit>(named("authApi"))) }

    single {
        provideNewsService(get<Retrofit>(named("newsApi")))
    }

    single {
        providePredictService(get<Retrofit>(named("predictApi")))
    }

    single {
        provideHistoryService(get<Retrofit>(named("predictApi")))
    }

    single { provideUserService(get<Retrofit>(named("userApi"))) }
}

private fun getHeaderInterceptor(preferenceManager: PreferenceManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers, preferenceManager)
}

fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)

fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

fun providePredictService(retrofit: Retrofit): PredictService = retrofit.create(PredictService::class.java)

fun provideHistoryService(retrofit: Retrofit): HistoryService = retrofit.create(HistoryService::class.java)

fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)


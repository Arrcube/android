package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.news.service.NewsService
import dev.agustacandi.learn.pestsentry.data.predict.service.PredictService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsNetworkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    single(named("newsApi")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        provideNewsService(get<Retrofit>(named("newsApi")))
    }
}

fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)
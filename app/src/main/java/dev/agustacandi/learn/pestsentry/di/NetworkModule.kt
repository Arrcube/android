package dev.agustacandi.learn.pestsentry.di

import dev.agustacandi.learn.pestsentry.BuildConfig
import dev.agustacandi.learn.pestsentry.data.auth.AuthService
import dev.agustacandi.learn.pestsentry.data.lib.HeaderInterceptor
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
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
//    single { provideAuthService(get()) }
}

private fun getHeaderInterceptor(preferenceManager: PreferenceManager): Interceptor {
    val headers = HashMap<String, String>()
    headers["Content-Type"] = "application/json"

    return HeaderInterceptor(headers, preferenceManager)
}

//fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)
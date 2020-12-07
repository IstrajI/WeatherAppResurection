package com.example.core.di

import com.example.core.network.api.OpenWeatherMapApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkingModule {
    @Provides
    @Singleton
    fun provideGithubApiService(restAdapter: Retrofit): OpenWeatherMapApi {
        return restAdapter.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRestAdapter(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60 * 1000.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(60 * 1000.toLong(), TimeUnit.MILLISECONDS).build()
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter("appid", "f6d9782fed94932664ea0eb44f9e66ad").build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }
}
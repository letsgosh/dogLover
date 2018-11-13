package com.br.travelapp.di.module

import android.content.Context
import com.br.doglove.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Module that provides network dependencies.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder().maxAge(1, TimeUnit.MINUTES).build()
            response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(cache: Cache, networkCacheInterceptor: Interceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }

//    @Provides
//    @Singleton
//    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL)
//                .addConverterFactory(MoshiConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(httpClient)
//                .build()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideTmdbApi(retrofit: Retrofit): TravelAPI {
//        return retrofit.create(TravelAPI::class.java)
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideTmdbRemoteRepository(travelAPI: TravelAPI): TravelRepository {
//        return TravelRepository(travelAPI)
//    }
}
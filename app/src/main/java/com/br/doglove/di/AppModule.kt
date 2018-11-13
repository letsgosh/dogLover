package com.br.doglove.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.br.doglove.DogLoveApplication
import com.br.doglove.sharedpreferences.Prefs
import com.br.travelapp.di.module.NetworkModule
import com.br.travelapp.di.module.ViewModelModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: DogLoveApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideApplication(application: DogLoveApplication): Application {
        return application
    }


    @Provides
    @Singleton
    fun provideGSON(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseFirestore {
        val firebaseDatabase = FirebaseFirestore.getInstance()
        return firebaseDatabase
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

//    @Singleton
//    fun provideAnalyticsHelper(context: Context): AnalyticsInterface {
//        return FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context))
//    }

    @Provides
    @Singleton
    fun providePrefs(context: Context): Prefs {
        return Prefs(context)
    }
}
package com.br.doglove.di

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseView
import androidx.room.Room
import com.br.doglove.DogLoveApplication
import com.br.doglove.data.AppDatabase
import com.br.doglove.sharedpreferences.Prefs
import com.br.travelapp.di.module.NetworkModule
import com.br.travelapp.di.module.ViewModelModule
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
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    internal fun providesAppDatabase(application: Application): AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "petlovers.db").build()



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
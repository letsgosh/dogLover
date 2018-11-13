package com.br.doglove.di

import com.br.doglove.DogLoveApplication

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BuildActivityModule::class])
interface AppComponent : AndroidInjector<DogLoveApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DogLoveApplication>()


//    fun context(): Context
//    fun app(): DogLoveApplication
//    fun gson(): Gson
//    fun firebaseStore(): BuildActivityModule
//    fun firebaseAuth(): FirebaseAuth
//    fun analyticsHelper(): AnalyticsInterface
//    fun configHelper(): ConfigInterface
//    fun taskReminderHelper(): TaskReminderInterface
//    fun prefs(): Prefs
}

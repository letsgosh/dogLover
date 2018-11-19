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
}

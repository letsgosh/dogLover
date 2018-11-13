package com.br.doglove.di

import com.br.doglove.ui.LoginActivity
import com.br.doglove.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity
}
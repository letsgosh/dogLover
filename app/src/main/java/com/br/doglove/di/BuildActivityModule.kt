package com.br.doglove.di

import com.br.doglove.ui.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment

}
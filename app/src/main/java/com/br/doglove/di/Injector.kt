package com.br.doglove.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.br.doglove.DogLoveApplication
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

object Injector {

    fun init(application: DogLoveApplication) {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is HasSupportFragmentInjector && activity is Injectable) {
                    AndroidInjection.inject(activity)
                }
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }
        })
    }
}
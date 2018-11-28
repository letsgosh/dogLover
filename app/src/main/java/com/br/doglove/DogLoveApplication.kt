package com.br.doglove

import android.content.Intent
import com.br.doglove.di.DaggerAppComponent
import com.br.doglove.di.Injector
import com.br.doglove.receiver.ConnectivityReceiver
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DogLoveApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<DogLoveApplication> =
            DaggerAppComponent.builder().create(this@DogLoveApplication)

    companion object {
        var instance: DogLoveApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        instance = this

    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }
}
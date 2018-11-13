package com.br.doglove.commons

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.doglove.DogLoveApplication
import com.br.doglove.receiver.ConnectivityReceiver
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    private lateinit var connectivityReceiver: ConnectivityReceiver


    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

        connectivityReceiver = ConnectivityReceiver()
        registerReceiver(connectivityReceiver, intentFilter)

        DogLoveApplication.instance?.setConnectivityListener(this)
    }

    fun showNoInternet() {
//        longSnackbarRed(findViewById(R.id.content), getString(com.br.rangetravel.R.string.no_internet_connection))
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected)
            showNoInternet()
    }

    override fun onStop() {
        super.onStop()
        try {
            unregisterReceiver(connectivityReceiver)
        } catch (ex: IllegalArgumentException) {
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
package com.br.doglove.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.LocationResult
import java.util.HashMap


class LocationUpdatesReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras
        if (extras != null) {
            val result = LocationResult.extractResult(intent)
//            if (result != null) {
                val location = result.lastLocation
//                ProfileManager.getInstance().setLocation(location)
//                if (updateOffers) {
//                    updateOffers = false
//                    OffersManager.getInstance().refresh(true)
//                }
//
//                val udid = ProfileManager.getInstance().getUdid()
//                if (udid != null) {
//                    val params = HashMap()
//                    params.put("latitude", location.latitude)
//                    params.put("longitude", location.longitude)
//                }
//            }
        }

    }
}

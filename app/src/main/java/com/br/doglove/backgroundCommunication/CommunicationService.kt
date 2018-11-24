package com.br.doglove.backgroundCommunication

import android.app.IntentService
import android.content.Intent

import com.br.doglove.R


class CommunicationService : IntentService("CommunicationService") {

    override fun onHandleIntent(intent: Intent?) {
        if (!intent!!.hasExtra(getString(R.string.INTENT_COMMUNICATION))) {
            return
        }

        val backgroundCommunication = intent.getSerializableExtra(getString(R.string.INTENT_COMMUNICATION)) as BackgroundCommunication
                ?: return

        backgroundCommunication.doCommunication()
    }
}

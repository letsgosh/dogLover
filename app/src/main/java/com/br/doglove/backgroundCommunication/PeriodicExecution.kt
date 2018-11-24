package com.br.doglove.backgroundCommunication

import android.content.Context
import android.content.Intent

import com.br.doglove.R


class PeriodicExecution(private val context: Context, private val backgroundCommunication: BackgroundCommunication) : Runnable {

    override fun run() {
        val intentService = Intent(context, CommunicationService::class.java)
        intentService.putExtra(context.getString(R.string.INTENT_COMMUNICATION), backgroundCommunication)
        context.startService(intentService)
    }
}

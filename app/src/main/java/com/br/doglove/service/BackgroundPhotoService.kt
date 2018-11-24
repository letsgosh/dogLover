package com.br.doglove.service

import android.content.Context


import com.br.doglove.backgroundCommunication.BackgroundCommunication
import com.br.doglove.backgroundCommunication.BackgroundCommunicationAction
import com.br.doglove.util.droid.camera.CameraUtil

import java.io.File
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BackgroundPhotoService : BackgroundCommunicationAction(), BackgroundCommunication {
    override fun doCommunication() {
        val photos = CameraUtil.photoFiles
        val service = PhotoService()

        if (photos != null) {
            for (photo in photos) {
                try {
                    Thread.sleep(10000)
                    service.uploadPhoto(photo)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
    }


    fun schedule(context: Context): ScheduledThreadPoolExecutor {
        return super.schedule(context, this, 0, 5, TimeUnit.MINUTES)
    }
}

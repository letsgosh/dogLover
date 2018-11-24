package com.br.doglove.util.droid.camera

import android.net.Uri

import java.io.File


class Photo {
    var path: String? = null
    var name: String? = null
    var picture: File? = null
    var position: CameraSlotEnum? = null
    private var wasShowed: Boolean = false
    var photoUri: Uri? = null
    var isUploaded: Boolean = false


    fun wasShowed(): Boolean {
        return wasShowed
    }

    fun setWasShowed(wasShowed: Boolean) {
        this.wasShowed = wasShowed
    }
}

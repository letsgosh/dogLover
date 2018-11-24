package com.br.doglove.util.droid.camera

import java.util.ArrayList


class CameraSlot {

    var photos: Array<Photo>? = null

    val photosUrl: List<String>
        get() {
            val urls = ArrayList<String>()
            for (p in photos!!) {
                if (p?.photoUri != null) {
                    urls.add(p.photoUri.toString())
                }
            }
            return urls
        }


    fun add(photo: Photo) {
        if(photos == null) {
            photo.setWasShowed(false)
            photos =  Array(3) {photo}
        }

        for (i in 0 until photos!!.size) {
            if (photos!![i] == null || photos!![i].picture == null) {
                photo.position = CameraSlotEnum.values()[i]
                photo.setWasShowed(false)
                photos!![i] = photo
                return
            }
        }
    }

    fun remove(position: CameraSlotEnum) {
        photos!![position.ordinal].picture = null
        photos!![position.ordinal].setWasShowed(false)
    }
}

package com.br.doglove.service

import java.io.File
import java.io.FileInputStream

import com.google.firebase.storage.FirebaseStorage
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask


class PhotoService {

    fun uploadPhoto(photo: File) {

        try {

            Thread.sleep(2000)
            val storage = FirebaseStorage.getInstance("gs://doglove-4907d.appspot.com")
            var storageRef = storage.reference
            val photosRef = storageRef.child("photos")

            Thread.sleep(2000)
            val pictureRef = photosRef.child(photo.name)
            val stream = FileInputStream(photo)
            val uploadTask = pictureRef.putStream(stream)

            Thread.sleep(2000)

            uploadTask.addOnFailureListener {
            }.addOnSuccessListener {

            }

        } catch (exception: Exception) {
        }

    }

}

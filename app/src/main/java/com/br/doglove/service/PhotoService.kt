package com.br.doglove.service

import android.util.Log
import androidx.annotation.NonNull
import com.br.doglove.R
import com.br.doglove.util.droid.camera.CameraUtil

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.ArrayList
import java.util.Arrays

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PhotoService {

    fun uploadPhoto(photo: File) {

        try {

            Thread.sleep(2000)
            val photosRef = FirebaseStorage.getInstance()
                    .getReferenceFromUrl(ApplicationBase.getAppContext().getString(R.string.firebase_storage_url))
                    .child(ApplicationConstants.FIREBASE_PHOTO_DIRECTORY)

            Thread.sleep(2000)
            val pictureRef = photosRef.child(photo.name)
            val stream = FileInputStream(photo)
            val uploadTask = pictureRef.putStream(stream)

            Thread.sleep(2000)

            uploadTask.addOnFailureListener(object : OnFailureListener() {
                fun onFailure(@NonNull exception: Exception) {

                }
            }).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>() {
                fun onSuccess(taskSnapshot: UploadTask.TaskSnapshot) {
                    val uri = ArrayList<String>()
                    uri.add(String.valueOf(taskSnapshot.getDownloadUrl()))
                    sendPhotosUrl(photo, uri)
                }
            })
        } catch (exception: Exception) {
        }

    }

}

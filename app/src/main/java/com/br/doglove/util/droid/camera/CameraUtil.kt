package com.br.doglove.util.droid.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import android.util.Log


import java.io.ByteArrayOutputStream
import java.io.File

object CameraUtil {

    val MEDIA_TYPE_IMAGE = 1
    val MEDIA_TYPE_VIDEO = 2
    val PATH_TO_SAVE_IMAGES = "Faz"
    val JPG_FORMAT = ".jpg"
    val MP4_FORMAT = ".mp4"
    val IMAGE_QUALITY = 60

    val photoFiles: Array<File>?
        get() {
            val file = File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), PATH_TO_SAVE_IMAGES)
            var listFile: Array<File>? = null

            if (file.isDirectory) {
                listFile = file.listFiles()
            }

            return listFile
        }

    fun getOutputMediaFile(type: Int, fileName: String): File? {
        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), PATH_TO_SAVE_IMAGES)

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }

        if (type == MEDIA_TYPE_IMAGE) {
            return File(mediaStorageDir.path + File.separator +
                    fileName + JPG_FORMAT)
        } else if (type == MEDIA_TYPE_VIDEO) {
            return File(mediaStorageDir.path + File.separator +
                    fileName + MP4_FORMAT)
        }

        return null
    }

    fun deleteFiles(filename: String) {
        val file = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), PATH_TO_SAVE_IMAGES)

        if (file.isDirectory) {
            for (i in 0 until file.listFiles().size) {
                if (file.listFiles()[i].name == filename) {
                    file.listFiles()[i].delete()
                    break
                }
            }
        }
    }

    fun rotate(data: ByteArray): ByteArray {
        val matrix = Matrix()
        matrix.postRotate(90f)
        val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
        if (bitmap.width < bitmap.height) {
            return data
        }
        val rotatedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, bitmap.width,
                bitmap.height, matrix, true
        )
        val blob = ByteArrayOutputStream()
        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, IMAGE_QUALITY, blob)
        return blob.toByteArray()
    }
}

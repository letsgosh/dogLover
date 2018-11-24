package com.br.doglove.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable

import com.br.doglove.R

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference

import androidx.appcompat.app.AppCompatActivity
import com.br.doglove.util.droid.camera.*
import kotlinx.android.synthetic.main.abc_activity_chooser_view.view.*
import kotlinx.android.synthetic.main.activity_photo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class OSPhotoActivity : AppCompatActivity() {

    private var camera: Camera? = null
    private var cameraPreview: CameraPreview? = null
    private lateinit var cameraSlot: CameraSlot

    private var shutterCallback: Camera.ShutterCallback = Camera.ShutterCallback { runOnUiThread { addBlinkAnimation(os_photo_blink) } }

    private val mPicture = Camera.PictureCallback { data, camera ->
        val p = Photo()
        addPhotoToSlot(p)
        var positionOfPhoto = getPhotoPosition(p)

        val pictureFile = CameraUtil.getOutputMediaFile(CameraUtil.MEDIA_TYPE_IMAGE,
                positionOfPhoto++.toString())

        try {
            val fos = FileOutputStream(pictureFile)
            fos.write(CameraUtil.rotate(data))
            fos.close()
            changePhotoFromSlot(p, positionOfPhoto)
            camera.startPreview()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        } catch (t: Throwable) {
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        camera = cameraInstance
        cameraPreview = CameraPreview(this, camera)
        loadMessage()
        val preview = findViewById<View>(R.id.camera_preview) as FrameLayout
        preview.addView(cameraPreview)

        showHideTutorial(false)
    }

    internal fun onCaptureTouched() {
        camera!!.takePicture(shutterCallback, null, mPicture)
    }

    internal fun onUnderstoodTouched() {
        showHideTutorial(false)
    }

    private fun showHideTutorial(show: Boolean) {
        if (show) {
            osphoto_overlay_camera.visibility = View.VISIBLE
            osphoto_bottom_bar.visibility = View.INVISIBLE
            button_capture.visibility = View.INVISIBLE
        } else {
            osphoto_overlay_camera.visibility = View.GONE
            osphoto_bottom_bar.visibility = View.VISIBLE
            button_capture.visibility = View.VISIBLE
        }
    }

    internal fun onSendPhotoTouched() {

    }

    internal fun onClosePhotoTouched() {
        osphoto_image_zoom.visibility = View.GONE
        oshphoto_image_zoom_close.visibility = View.GONE
    }

    private fun loadMessage() {

    }

    fun presentFirebaseException() {
        Toast.makeText(this, getString(R.string.firabase_exception), Toast.LENGTH_SHORT)
    }

    fun presentOSDetail(status: String, statusCode: String) {
    }

    fun presentHomePage() {

    }

    fun presentPhotoToCameraSlot(cameraSlot: CameraSlot) {
        val bmp: Bitmap? = null
        for (p in cameraSlot.photos) {
            if (p != null && !p.wasShowed()) {
                when (p.position) {
                    CameraSlotEnum.FIRST -> showPhoto(p, osphoto_first_photo, osphoto_delete_first_photo, bmp)
                    CameraSlotEnum.SECOND -> showPhoto(p, osphoto_second_photo, osphoto_delete_second_photo, bmp)
                    CameraSlotEnum.THIRD -> showPhoto(p, osphoto_third_photo, osphoto_delete_third_photo, bmp)
                }
            }
        }
    }

    fun showPhotoZoom(p: Photo) {
        if (p.picture != null) {
            val bmp = BitmapFactory.decodeFile(p.picture.absolutePath)
//            osphoto_image_zoom.image = bmp
            osphoto_image_zoom.visibility = View.VISIBLE
            oshphoto_image_zoom_close.visibility = View.VISIBLE
        }
    }

    private fun showPhoto(p: Photo, imageView: ImageView, closeImageView: ImageView, bmp: Bitmap?) {
        var bmp = bmp
        if (p.picture != null) {
            bmp = BitmapFactory.decodeFile(p.picture.absolutePath)
            imageView.setImageBitmap(bmp)
            p.setWasShowed(true)
            closeImageView.visibility = View.VISIBLE

        } else {
            imageView.setImageResource(R.drawable.empty_photo)
            p.setWasShowed(false)
            closeImageView.visibility = View.INVISIBLE
        }
    }

    fun onDeletePhoto(view: View) {
        when (view.id) {
            R.id.osphoto_delete_first_photo -> deletePhotoFromSlot(CameraSlotEnum.FIRST)
            R.id.osphoto_delete_second_photo -> deletePhotoFromSlot(CameraSlotEnum.SECOND)
            R.id.osphoto_delete_third_photo -> deletePhotoFromSlot(CameraSlotEnum.THIRD)
        }
    }

    fun onOpenPhoto(view: View) {
        when (view.id) {
            R.id.osphoto_first_photo -> getPhotoFromSlot(CameraSlotEnum.FIRST)
            R.id.osphoto_second_photo -> getPhotoFromSlot(CameraSlotEnum.SECOND)
            R.id.osphoto_third_photo -> getPhotoFromSlot(CameraSlotEnum.THIRD)
        }
    }

    private fun addBlinkAnimation(myComponent: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 100
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 1
        myComponent.startAnimation(anim)
    }


    fun sendPhotoToService(p: Photo, uri: Uri) {
        val position = cameraSlot.photos.indexOf(p)
        cameraSlot.photos[position].isUploaded = true
        cameraSlot.photos[position].photoUri = uri

        val wasAllPhotosUploaded = verifyWhetherAllPhotoIsUploadedToFirebase()

        if (wasAllPhotosUploaded) {
            this.sendPhotosUrl()
        }
    }

    private fun verifyWhetherAllPhotoIsUploadedToFirebase(): Boolean {
        val photos = this.cameraSlot.photos
        if (!Arrays.asList<Array<Photo>>(photos).isEmpty()) {
            for (photo in photos) {
                if (photo != null && !photo.isUploaded) {
                    return false
                }
            }
            return true
        }
        return false
    }

    fun sendPhotosUrl() {
        
    }
    

    fun addPhotoToSlot(p: Photo) {
        cameraSlot.add(p)
    }

    fun changePhotoFromSlot(p: Photo, position: Int) {
        if (p.picture != null) {
            p.path = p.picture.path
            p.name = p.picture.name
        }
        cameraSlot.photos[position] = p
        presentPhotoToCameraSlot(cameraSlot)
    }

    fun getPhotoPosition(p: Photo): Int {
        return cameraSlot.photos.indexOf(p)
    }

    fun failToUploadFirebase() {
//        dismissProgressDialog()
        presentFirebaseException()
    }

    fun deletePhotoFromSlot(picturePosition: CameraSlotEnum) {
        cameraSlot.remove(picturePosition)
        presentPhotoToCameraSlot(cameraSlot)
    }

    fun getPhotoFromSlot(position: CameraSlotEnum) {
        if (cameraSlot.photos != null && cameraSlot.photos[position.ordinal] != null) {
            showPhotoZoom(cameraSlot.photos[position.ordinal])
        }
    }

    override fun onPause() {
        if (camera != null) {
            camera!!.release()
            camera = null
        }
        super.onPause()
    }

    companion object {

        val cameraInstance: Camera?
            get() {
                var c: Camera? = null
                try {
                    c = Camera.open()
                    c!!.setDisplayOrientation(90)
                    val params = c.parameters
                    params.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE
                    c.enableShutterSound(true)

                    val sizes = params.supportedPictureSizes

                    var mSize: Camera.Size? = null
                    for (size in sizes) {
                        if (mSize == null || mSize.width > size.width) {
                            mSize = size
                        }
                    }
                    params.setPictureSize(mSize!!.width, mSize.height)
                    c.parameters = params
                } catch (e: Exception) {
                }

                return c
            }
    }
}

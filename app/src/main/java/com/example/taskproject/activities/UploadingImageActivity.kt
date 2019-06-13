package com.example.taskproject.activities

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.taskproject.Presenter.UploadImagePresenter
import com.example.taskproject.R
import com.example.taskproject.contract.UploadImageContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_uploading_image.*
import java.io.ByteArrayOutputStream

class UploadingImageActivity : AppCompatActivity(), UploadImageContract.View {



    private val channelName = "channelName"
    private val channelId = "1"
    private val channelDesc = "channelDesc"
    private val RC_SELECT_IMAGE = 2
    private  lateinit var imagePath : String
    private lateinit var uploadingImagePresenter: UploadImagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploading_image)
        initPresenter()
        setImageViewListener()
        setUploadButtonListener()
    }

    private fun setUploadButtonListener() {
        uploadImageUploadButton.setOnClickListener {
            uploadingImagePresenter.requestToUploadImage(imagePath)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelName
            val descriptionText = channelDesc
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun initPresenter() {
        uploadingImagePresenter = UploadImagePresenter(this)

    }

    private fun setImageViewListener() {
        uploadImageImageView.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
                putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
            }
            startActivityForResult(Intent.createChooser(intent, "Select Image"), RC_SELECT_IMAGE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SELECT_IMAGE && resultCode == Activity.RESULT_OK
            && data != null && data.data!= null){
            val selectedImagePath = data.data
            imagePath = selectedImagePath.toString()
            setImageSrc(selectedImagePath)
        }
    }



    private fun setImageSrc(selectedImagePath: Uri?) {
        Picasso.get().load(selectedImagePath).noPlaceholder().centerCrop().fit().into(uploadImageImageView)
    }

    override fun startNotification() {
        createNotificationChannel()
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("uploaded")
            .setContentText("image uploaded successfuly")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(2, builder.build())
        }
    }


}

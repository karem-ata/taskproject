package com.example.taskproject.contract

import android.net.Uri

interface UploadImageContract {

    interface View{
        fun startNotification()
    }

    interface Presenter{
        fun requestToUploadImage(filePath: String)

        interface onFinished{
            fun onFailure(t: Throwable)
            fun onFinishedListener()
        }
    }

    interface Interactor{
        fun uploadImage(imagePath: String)
    }
}
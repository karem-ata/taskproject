package com.example.taskproject.Presenter

import android.net.Uri
import android.util.Log
import com.example.taskproject.contract.UploadImageContract
import com.example.taskproject.interactor.UploadImageInteractor

class UploadImagePresenter(private val view: UploadImageContract.View):
    UploadImageContract.Presenter, UploadImageContract.Presenter.onFinished {


    private val uploadInteractor = UploadImageInteractor(this)

    override fun onFailure(t: Throwable) {
        Log.e("message", t.message)
    }

    override fun onFinishedListener() {
        view.startNotification()
    }

    override fun requestToUploadImage(filePath: String) {
        uploadInteractor.uploadImage(filePath)
    }
}
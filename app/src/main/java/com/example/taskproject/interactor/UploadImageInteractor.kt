package com.example.taskproject.interactor

import android.net.Uri
import android.util.Log
import com.example.taskproject.contract.UploadImageContract
import com.example.taskproject.network.ApiClient
import com.example.taskproject.network.SearchInterface
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import android.provider.MediaStore




class UploadImageInteractor(private val onFinishedListener: UploadImageContract.Presenter.onFinished): UploadImageContract.Interactor {


    override fun uploadImage(imagePath: String) {
        val headers = HashMap<String, String>()

        headers["x-lang-code"] = "en-us"
        headers["x-auth-token"] = "e7cde7086fb857a39e5fb4e5567eecb0"
        headers["X-user-type"] = "0"

        val apiClient = ApiClient().getClient()?.create(SearchInterface::class.java)

        val file = File(imagePath)


        val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image_profile", file.name, fileReqBody)
        val apiService = apiClient?.uploadImage(headers, part)

        apiService?.enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("failureUrl",t.localizedMessage)
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("responseUrl", call.request().url().toString())
                response.body()?.let {
                    onFinishedListener.onFinishedListener()
                    Log.e("finished", "sdfsdfsd")
                }
            }
        })
    }
}
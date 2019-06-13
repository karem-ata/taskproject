package com.example.taskproject.network

import com.example.taskproject.model.Account
import com.example.taskproject.util.Constants
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SearchInterface {
    @POST(Constants.API_VERSION + Constants.BUSINESS + Constants.PHOTO + Constants.SEARCH)
    @FormUrlEncoded
    fun getAccounts(@HeaderMap headers:HashMap<String,String>,@Field("keyword")keyword: String?):Call<Account>

    @Multipart
    @POST(Constants.API_VERSION + Constants.PHOTOGRAPHER +Constants.PROFILE + Constants.UPLOAD)
    fun uploadImage(@HeaderMap headers: HashMap<String, String>, @Part file: MultipartBody.Part): Call<String>
}
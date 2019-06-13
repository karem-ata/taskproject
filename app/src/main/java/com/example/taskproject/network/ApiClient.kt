package com.example.taskproject.network

import com.example.taskproject.util.Constants
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ApiClient {
    private var retrofit: Retrofit?= null
    fun getClient():Retrofit?{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getRetrofiClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }

    fun getRetrofiClient(): OkHttpClient{
        val spac = ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT)
            .build()
        return OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spac)).build()
    }
}
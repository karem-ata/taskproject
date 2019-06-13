package com.example.taskproject.interactor

import android.util.Log
import com.example.taskproject.contract.SearchContract
import com.example.taskproject.model.Account
import com.example.taskproject.network.ApiClient
import com.example.taskproject.network.SearchInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchInteractor(private val onFinishedListener: SearchContract.Presenter.onFinished): SearchContract.Interactor {

    override fun getAccounts(keyword: String?) {
        val headers = HashMap<String, String>()

        headers["x-lang-code"] = "en-us"
        headers["x-auth-token"] = "a3a982607f00d44117ef0172508860a1"
        headers["X-user-type"] = "1"
        val apiClient = ApiClient().getClient()?.create(SearchInterface::class.java)
        val apiService = apiClient?.getAccounts(headers, keyword)


        apiService?.enqueue(object: Callback<Account> {
            override fun onFailure(call: Call<Account>, t: Throwable) {
                Log.e("failureUrl",t.localizedMessage)
                onFinishedListener.onFailure(t)
            }

            override fun onResponse(call: Call<Account>, response: Response<Account>) {
                Log.e("responseUrl", call.request().url().toString())
                response.body()?.let {
                    onFinishedListener.onAccountsCalledFinished(it)
                }
            }
        })
    }
}
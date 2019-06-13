package com.example.taskproject.contract

import com.example.taskproject.model.Account

interface SearchContract {

    interface View{
        fun hideEmptyView()
        fun showRecyclerView()
        fun updateRecyclerView()
        fun setResultsNum(resultsNum: String)
    }

    interface accountView{
        fun setAccountImage(accountUrl: String?)
        fun setAccountName(accountName: String?)
        fun setAccountEmail(accountEmail: String?)
        fun setAccountFollowers(accountFollowers: Int?)
        fun setAccountFollowings(accountFollowings: Int?)
        fun setAccountPhotoNum(accountPhotoNum: Int?)
    }

    interface Presenter{
        fun requestAccounts(keyword: String?)
        fun onBindAccount(viewHolder: accountView, position: Int)
        fun getAccountCounts():Int

        interface onFinished{
            fun onFailure(throwable: Throwable)
            fun onAccountsCalledFinished(accountsResponse: Account)
        }
    }

    interface Interactor{
        fun getAccounts(keyword: String?)
    }

}
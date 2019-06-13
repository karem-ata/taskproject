package com.example.taskproject.Presenter

import android.util.Log
import com.example.taskproject.contract.SearchContract
import com.example.taskproject.interactor.SearchInteractor
import com.example.taskproject.model.Account
import com.example.taskproject.model.Data

class SearchPresenter(private val view: SearchContract.View ): SearchContract.Presenter, SearchContract.Presenter.onFinished {

    private var accountsArraylist = ArrayList<Data>()
    private var searchInteractor = SearchInteractor(this)

    override fun requestAccounts(keyword: String?) {
        Log.e("keyword", keyword)
        searchInteractor.getAccounts(keyword)
    }

    override fun onBindAccount(viewHolder: SearchContract.accountView, position: Int) {
        val currentAccount = accountsArraylist.get(position)
        viewHolder.setAccountEmail(currentAccount.photographer.username)
        viewHolder.setAccountName(currentAccount.photographer.fullName)
        if (currentAccount.businessPurchase.size > 0) {
            viewHolder.setAccountFollowers(currentAccount.businessPurchase[0].followersCounts)
            viewHolder.setAccountFollowings(currentAccount.businessPurchase[0].followingsCounts)
        }
        if (currentAccount.lightBox.size > 0)
            viewHolder.setAccountPhotoNum(currentAccount.lightBox[0].photosCount)
        viewHolder.setAccountImage(currentAccount.url)
    }

    override fun getAccountCounts(): Int {
        return  accountsArraylist.size
    }

    override fun onFailure(t: Throwable) {

    }

    override fun onAccountsCalledFinished(accountsResponse: Account) {
        accountsArraylist = accountsResponse.data.data
        Log.e("accounts size" , accountsArraylist.size.toString())
        view.setResultsNum(accountsArraylist.size.toString())
        view.hideEmptyView()
        view.showRecyclerView()
        view.updateRecyclerView()
    }
}
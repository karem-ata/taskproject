package com.example.taskproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskproject.Presenter.SearchPresenter
import com.example.taskproject.R
import com.example.taskproject.contract.SearchContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.account_item.view.*

class SearchRecyclerViewAdapter(val searchPresenter: SearchPresenter):RecyclerView.Adapter<SearchRecyclerViewAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_item, parent, false)
        return AccountViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchPresenter.getAccountCounts()
    }

    override fun onBindViewHolder(viewHolder: AccountViewHolder, position: Int) {
       searchPresenter.onBindAccount(viewHolder, position)
    }

    inner class AccountViewHolder(private val view: View): RecyclerView.ViewHolder(view), SearchContract.accountView{
        override fun setAccountImage(accountUrl: String?) {
            Picasso.get().load(accountUrl).into(view.accountImageView)
        }

        override fun setAccountFollowers(accountFollowers: Int?) {
            view.accountFollowers.text = accountFollowers.toString() + " followers"
        }

        override fun setAccountFollowings(accountFollowings: Int?) {
            view.accountFollowing.text = accountFollowings.toString() + " followings"
        }

        override fun setAccountPhotoNum(accountPhotoNum: Int?) {
            view.photoNum.text = accountPhotoNum.toString() + " photos"
        }


        override fun setAccountName(accountName: String?) {
            view.accountName.text = "@"+accountName
        }

        override fun setAccountEmail(accountEmail: String?) {
            view.accountEmail.text = accountEmail
        }
    }
}
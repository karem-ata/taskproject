package com.example.taskproject.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import com.example.taskproject.Presenter.SearchPresenter
import com.example.taskproject.R
import com.example.taskproject.adapter.SearchRecyclerViewAdapter
import com.example.taskproject.contract.SearchContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class MainActivity : AppCompatActivity(), SearchContract.View {

    lateinit var searchPresenter:SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAppBarLayoutListener()
        setSearchViewListener()
        initPresenter()
        initSearchRecyclerView()
        setFabButtonListener()
    }

    private fun setFabButtonListener() {
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, UploadingImageActivity::class.java)
            startActivity(intent)
        }
    }


    private fun initPresenter() {
        searchPresenter = SearchPresenter(this)
    }

    private fun initSearchRecyclerView(){
        setRecyclerViewLayoutManager()
        setRecyclerViewAdapter()
    }

    private fun setRecyclerViewAdapter() {
        val adapter = SearchRecyclerViewAdapter(searchPresenter)
        searchRecyclerView.adapter = adapter
    }

    private fun setRecyclerViewLayoutManager() {
        val layoutManage = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        searchRecyclerView.layoutManager = layoutManage
    }

    private fun setSearchViewListener() {
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchPresenter.requestAccounts(query)
                return true
            }

            override fun onQueryTextChange(queryText: String?): Boolean {
                return true

            }
        })
    }

    private fun setAppBarLayoutListener() {
        var isShow = true
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            private var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset in -100..100) {
                    toolbar.title = "Search"
                    isShow = true
                } else if (isShow) {
                    searchActivityCollapsingLayout.title = ""
                    toolbar.title=""
                    isShow = false
                }
            }
        })
    }

    override fun setResultsNum(resultsNum: String) {
        searchAppBarResultsNumTextView.text = resultsNum + "results"
    }

    override fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    override fun showRecyclerView() {
        searchRecyclerView.visibility = View.VISIBLE
    }

    override fun updateRecyclerView() {
        searchRecyclerView.adapter?.notifyDataSetChanged()
    }
}
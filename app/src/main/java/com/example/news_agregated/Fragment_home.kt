package com.example.news_agregated

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_agregated.adapter.Adapter
import com.example.news_agregated.util.Resource


class Fragment_home : Fragment(R.layout.fragment_home2) {
    lateinit var rv_view_home: RecyclerView
    lateinit var progressbar: ProgressBar
    val TAG = "HomeBreakingNewsFragment"
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: Adapter

    @SuppressLint("LongLogTag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecycleView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Soccess -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "error:$message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progressbar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressbar.visibility = View.VISIBLE

    }

    private fun setupRecycleView() {
        newsAdapter = Adapter()
        rv_view_home.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}

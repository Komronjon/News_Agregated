package com.example.news_agregated

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_agregated.adapter.Adapter
import com.example.news_agregated.util.Constans.Companion.SEARCH_NEWS_TIME_DELAY
import com.example.news_agregated.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchViewFragment : Fragment(R.layout.fragment_search_news) {
    lateinit var newsAdapter: Adapter
    lateinit var rvSearchNews: RecyclerView
    lateinit var progressbar: ProgressBar
    val TAG = "SearchNewsFragment"
    lateinit var viewModel: NewsViewModel
    lateinit var etSearch: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecycleView()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable.let {
             if (editable.toString().isNotEmpty()){
                 viewModel.searchNews(editable.toString())
             }

                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
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
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

    }
}



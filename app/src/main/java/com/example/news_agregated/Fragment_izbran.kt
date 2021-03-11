package com.example.news_agregated

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment


class Fragment_izbran : Fragment(R.layout.fragment_izbran) {
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }


}
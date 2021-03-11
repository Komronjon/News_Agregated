package com.example.news_agregated.models


data class NewsResponse(

    val articles: List<Article>,

    val status: String,
    val totalResults: Int
)
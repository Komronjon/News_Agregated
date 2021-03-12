package com.example.news_agregated.repository

import com.example.news_agregated.api.Retrofit_stanse
import com.example.news_agregated.db.ArticleDatabase

class   NewsRepository (
        val db: ArticleDatabase
        )
{
        suspend fun getHomeFragment(countryCode:String,pageNumber:Int)=
                Retrofit_stanse.api.getFragmentHome(countryCode,pageNumber)

        suspend fun searchNews(searchQuery: String,pageNumber: Int)=
                Retrofit_stanse.api.getFragmentHome(searchQuery,pageNumber)

}
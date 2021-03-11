package com.example.news_agregated.api

import com.example.news_agregated.models.NewsResponse
import com.example.news_agregated.util.Constans.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APINews {
  @GET("v2/top-headlines")
   suspend fun getFragmentHome(
      @Query("country")
      countryCode:String="us",
      @Query("page")
      pageNumber: Int=1,
      @Query("apiKey")
      apiKey:String=API_KEY

   ):Response<NewsResponse>

    @GET("v2/top-everything")
    suspend fun getFragmentfavorite(
        @Query("country")
        countryCode:String="us",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey:String=API_KEY

    ):Response<NewsResponse>
}
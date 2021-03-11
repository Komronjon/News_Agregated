package com.example.news_agregated

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_agregated.models.NewsResponse
import com.example.news_agregated.repository.NewsRepository
import com.example.news_agregated.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
        val newwsRepository: NewsRepository
):ViewModel() {
    val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
 var breakingNewspage=1
    init {
        getHomeFragmentNews("us")
    }
    fun getHomeFragmentNews(countryCode:String)=viewModelScope.launch {
       breakingNews.postValue(Resource.Loading())
        val resource=newwsRepository.getHomeFragment(countryCode,breakingNewspage)
       breakingNews.postValue(handleHomeNewsResponse(resource))
    }
    private fun handleHomeNewsResponse(response: Response<NewsResponse>):Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Soccess(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
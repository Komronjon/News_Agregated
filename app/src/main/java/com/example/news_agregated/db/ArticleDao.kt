package com.example.news_agregated.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.news_agregated.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long


    @Query("SELECT *  FROM articles")
    fun getAllArticles():LiveData<List<Article>>


    @Delete
    suspend fun  delecteArticle(article: Article)

}
package com.example.news_agregated.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news_agregated.models.Article

@Database(
        entities = [Article::class],
        version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    //   ArticleDatabase()
    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        fun get(application: Application): ArticleDatabase {
            return Room.databaseBuilder(application, ArticleDatabase::class.java, "article_db").build()
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
        ).fallbackToDestructiveMigration()
                .build()

    }
}
package com.example.news_agregated.db

import androidx.room.TypeConverter
import com.example.news_agregated.models.Source

class Converters {
    @TypeConverter
    fun fromSoures(source: Source):String{
return source.name
    }


    @TypeConverter
    fun toSourse(name: String): Source {
        return Source(name,name)
    }
}
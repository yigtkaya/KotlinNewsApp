package com.kaya.newsapp.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kaya.newsapp.domain.models.Source
import com.squareup.moshi.Moshi


class SourceTypeConverter {

    // create a TypeConverter to convert a Source object to a String
    @TypeConverter
    fun fromSourceToString(source: Source): String {
        return Gson().toJson(source)
    }


    // create a TypeConverter to convert a String to a Source object
    @TypeConverter
    fun fromStringToSource(json: String): Source {
        return Gson().fromJson(json, Source::class.java)
    }

}
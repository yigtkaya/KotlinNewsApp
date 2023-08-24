package com.kaya.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kaya.newsapp.data.mapper.SourceTypeConverter

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract val articleDao : ArticleDao
}
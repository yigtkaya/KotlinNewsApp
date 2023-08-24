package com.kaya.newsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articleEntities : List<ArticleEntity>)

    @Query("DELETE FROM ArticleEntity")
    suspend fun deleteAllArticles()

    @Query("""
        SELECT * FROM ArticleEntity
        WHERE title LIKE '%' || :searchQuery || '%'
        OR description LIKE '%' || :searchQuery || '%'
    """)
    suspend fun searchArticles(searchQuery : String) : List<ArticleEntity>

}
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
    @Query("DELETE  FROM ArticleEntity WHERE type = :selectedTab")
    suspend fun deleteArticlesByType(selectedTab : String)

    @Query("""
        SELECT * FROM ArticleEntity
        WHERE type = :selectedTab
        AND (title LIKE '%' || :searchQuery || '%' OR description LIKE '%' || :searchQuery || '%')
    """)
    suspend fun searchArticles(searchQuery : String, selectedTab: String) : List<ArticleEntity>


    // write a query that returns all articles from the database that have query in their title or description type is not important
    @Query("""
        SELECT * FROM ArticleEntity
        WHERE title LIKE '%' || :searchQuery || '%' OR description LIKE '%' || :searchQuery || '%'
    """)
    suspend fun searchAllArticles(searchQuery : String) : List<ArticleEntity>



}
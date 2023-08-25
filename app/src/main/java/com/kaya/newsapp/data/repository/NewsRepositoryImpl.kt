package com.kaya.newsapp.data.repository

import com.kaya.newsapp.data.local.ArticleDatabase
import com.kaya.newsapp.data.mapper.toArticle
import com.kaya.newsapp.data.mapper.toArticleEntity
import com.kaya.newsapp.data.remote.NewsApi
import com.kaya.newsapp.domain.models.Article
import com.kaya.newsapp.domain.repository.NewsRepository
import com.kaya.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDatabase: ArticleDatabase,
) : NewsRepository {

    private val dao = articleDatabase.articleDao

    override suspend fun getNews(
        fetchFromRemote: Boolean,
        searchQuery: String,
        selectedTab: String
    ): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery, selectedTab)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle(selectedTab) }
            ))

            val isDbEmpty = localArticles.isEmpty()
            val shouldJustLoadFromCache = !fetchFromRemote && !isDbEmpty

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = newsApi.getNews()

                if(response.isSuccessful) {
                    response.body()?.articles?.map { it.toArticle(selectedTab) } ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            }

            remoteListings.let {
                dao.deleteArticlesByType(selectedTab)
                dao.insertArticles(it.map { article -> article.toArticleEntity(selectedTab) })

                emit(Resource.Success(
                    data = dao.searchArticles("", selectedTab).map { article -> article.toArticle(selectedTab) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getSportsNews(fetchFromRemote: Boolean, searchQuery: String, selectedTab: String
    ): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery, selectedTab)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle(selectedTab) }
            ))

            val isDbEmpty = localArticles.isEmpty()
            val shouldJustLoadFromCache = !fetchFromRemote && !isDbEmpty

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = newsApi.getSportsNews()

                if(response.isSuccessful) {
                    response.body()?.articles?.map { it.toArticle(selectedTab) } ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            }

            remoteListings.let {
                dao.deleteArticlesByType(selectedTab)
                dao.insertArticles(it.map { article -> article.toArticleEntity(selectedTab) })

                emit(Resource.Success(
                    data = dao.searchArticles("", selectedTab).map { article -> article.toArticle(selectedTab) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getHealthNews(fetchFromRemote: Boolean, searchQuery: String, selectedTab: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery, selectedTab)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle(selectedTab) }
            ))

            val isDbEmpty = localArticles.isEmpty()
            val shouldJustLoadFromCache = !fetchFromRemote && !isDbEmpty

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = newsApi.getHealthNews()

                if(response.isSuccessful) {
                    response.body()?.articles?.map { it.toArticle(selectedTab) } ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            }

            remoteListings.let {
                dao.deleteArticlesByType(selectedTab)
                dao.insertArticles(it.map { article -> article.toArticleEntity(selectedTab) })

                emit(Resource.Success(
                    data = dao.searchArticles("", selectedTab).map { article -> article.toArticle(selectedTab) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getFinanceNews(fetchFromRemote: Boolean, searchQuery: String, selectedTab: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery, selectedTab)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle(selectedTab) }
            ))

            val isDbEmpty = localArticles.isEmpty()
            val shouldJustLoadFromCache = !fetchFromRemote && !isDbEmpty

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = newsApi.getFinanceNews()

                if(response.isSuccessful) {
                    response.body()?.articles?.map { it.toArticle(selectedTab) } ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            }

            remoteListings.let {
                dao.deleteArticlesByType(selectedTab)
                dao.insertArticles(it.map { article -> article.toArticleEntity(selectedTab) })

                emit(Resource.Success(
                    data = dao.searchArticles("", selectedTab).map { article -> article.toArticle(selectedTab) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getAllNews(fetchFromRemote: Boolean, searchQuery: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchAllArticles(searchQuery)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle(null) }
            ))

            val isDbEmpty = localArticles.isEmpty()
            val shouldJustLoadFromCache = !fetchFromRemote && !isDbEmpty

            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = newsApi.getNews()

                if(response.isSuccessful) {
                    response.body()?.articles?.map { it.toArticle(null) } ?: emptyList()
                } else {
                    emptyList()
                }

            } catch (e: IOException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            } catch (e: HttpException) {
                emit(Resource.Error(e.message.toString()))
                return@flow
            }

            remoteListings.let {
                dao.deleteAllArticles()
                dao.insertArticles(it.map { article -> article.toArticleEntity(null) })

                emit(Resource.Success(
                    data = dao.searchAllArticles("").map { article -> article.toArticle(null) }
                ))
                emit(Resource.Loading(false))
            }
        }
    }
}
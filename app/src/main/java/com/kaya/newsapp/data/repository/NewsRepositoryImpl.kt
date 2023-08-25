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

    override suspend fun getNews(fetchFromRemote: Boolean, searchQuery: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle() }
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
                    response.body()?.articles?.map { it.toArticle() } ?: emptyList()
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
                dao.insertArticles(it.map { article -> article.toArticleEntity() })

                emit(Resource.Success(
                    data = dao.searchArticles("").map { article -> article.toArticle() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getSportsNews(fetchFromRemote: Boolean, searchQuery: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle() }
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
                    response.body()?.articles?.map { it.toArticle() } ?: emptyList()
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
                dao.insertArticles(it.map { article -> article.toArticleEntity() })

                emit(Resource.Success(
                    data = dao.searchArticles("").map { article -> article.toArticle() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getHealthNews(fetchFromRemote: Boolean, searchQuery: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle() }
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
                    response.body()?.articles?.map { it.toArticle() } ?: emptyList()
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
                dao.insertArticles(it.map { article -> article.toArticleEntity() })

                emit(Resource.Success(
                    data = dao.searchArticles("").map { article -> article.toArticle() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getFinanceNews(fetchFromRemote: Boolean, searchQuery: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading(true))
            val localArticles = dao.searchArticles(searchQuery)
            emit(Resource.Success(
                data = localArticles.map { it.toArticle() }
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
                    response.body()?.articles?.map { it.toArticle() } ?: emptyList()
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
                dao.insertArticles(it.map { article -> article.toArticleEntity() })

                emit(Resource.Success(
                    data = dao.searchArticles("").map { article -> article.toArticle() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }


}
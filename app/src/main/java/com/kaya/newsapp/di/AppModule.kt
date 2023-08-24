package com.kaya.newsapp.di

import android.app.Application
import androidx.room.Room
import com.kaya.newsapp.data.local.ArticleDatabase
import com.kaya.newsapp.data.remote.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application) : ArticleDatabase {
        return Room.databaseBuilder(
            app,
            ArticleDatabase::class.java,
            "articledb.db"
        ).build()

    }
}
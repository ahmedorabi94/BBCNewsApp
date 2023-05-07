package com.example.bbcnewsapp.features.new_list.di


import com.example.bbcnewsapp.core.data.ApiService
import com.example.bbcnewsapp.core.repo.remote.NewsDataSource
import com.example.bbcnewsapp.features.new_list.framework.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsListModule {


    @Singleton
    @Provides
    fun provideInApiNewsListDataSource(apiService: ApiService): NewsDataSource {
        return NewsRepositoryImpl(apiService)
    }


}
package com.example.bbcnewsapp.core.repo.remote

import javax.inject.Inject

class NewsRepository @Inject constructor(private val dataSource: NewsDataSource) {

    suspend fun getNewsResponse() = dataSource.getNewsResponse()

}
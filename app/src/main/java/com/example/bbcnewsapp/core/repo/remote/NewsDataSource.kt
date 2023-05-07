package com.example.bbcnewsapp.core.repo.remote

import com.example.bbcnewsapp.core.data.Resource
import com.example.bbcnewsapp.core.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    suspend fun getNewsResponse(): Flow<Resource<List<Article>>>

}
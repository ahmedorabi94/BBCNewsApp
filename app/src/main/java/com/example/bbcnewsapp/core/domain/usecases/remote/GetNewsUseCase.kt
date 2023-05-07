package com.example.bbcnewsapp.core.domain.usecases.remote


import com.example.bbcnewsapp.core.data.Resource
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.core.repo.remote.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {


    suspend operator fun invoke(): Flow<Resource<List<Article>>> {
        return newsRepository.getNewsResponse()
    }

}
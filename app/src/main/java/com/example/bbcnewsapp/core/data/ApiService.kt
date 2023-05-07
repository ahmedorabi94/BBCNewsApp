package com.example.bbcnewsapp.core.data

import com.example.bbcnewsapp.BuildConfig
import com.example.bbcnewsapp.core.domain.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("top-headlines?apiKey=499c1384b1f1467c90f8276feeb2a2ba")
    suspend fun getNewsResponseAsync(@Query("sources") sources: String = BuildConfig.sources): NewsResponse


}
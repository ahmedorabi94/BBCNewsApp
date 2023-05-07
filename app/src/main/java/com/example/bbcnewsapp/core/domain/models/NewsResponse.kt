package com.example.bbcnewsapp.core.domain.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
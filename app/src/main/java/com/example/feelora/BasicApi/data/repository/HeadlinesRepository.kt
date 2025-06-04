package com.example.feelora.BasicApi.data.repository

import com.example.feelora.BasicApi.data.model.NewsResponse
import com.example.feelora.BasicApi.data.network.ApiService

class HeadlinesRepository(private val api: ApiService) {

    suspend fun fetchHeadlines(): NewsResponse {
        return api.getHeadlines(
            query = "kecemasan",
            from = "2024-11-09",
            sortBy = "publishedAt",
            apiKey = "4b3b6e9d0dc644deb541678e8178b392"
        )
    }
}


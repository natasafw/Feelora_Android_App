package com.example.feelora.BasicApi.data.network

import com.example.feelora.BasicApi.data.model.Headlines
import com.example.feelora.BasicApi.data.model.JournalRequest
import com.example.feelora.BasicApi.data.model.JournalResponse
import com.example.feelora.BasicApi.data.model.NewsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getHeadlines(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    @POST("data_journal")
    suspend fun createJournal(
        @Header("Authorization") token: String,
        @Body products: List<JournalRequest>,
    ): JournalResponse

    @GET("data_journal")
    suspend fun getJournal(
        @Header("Authorization") token: String
    ): JournalResponse

    @DELETE("data_journal/{uuid}")
    suspend fun deleteJournal(
        @Header("Authorization") token: String,
        @Path("uuid") uuid: String
    )
}


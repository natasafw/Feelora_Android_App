package com.example.feelora.BasicApi.data.repository

import com.example.feelora.BasicApi.data.model.JournalRequest
import com.example.feelora.BasicApi.data.model.JournalResponse
import com.example.feelora.BasicApi.data.network.ApiService

class JournalRepository(
    private val api: ApiService,
) {
    private val tokenBearer = "Bearer GDkH5qXpoK8IWqS01Dgdj7oJzOPiV6yzbSSZtRFvfhBcuqBIMQ"

    suspend fun fetchJournal(): JournalResponse {
        return api.getJournal(tokenBearer)
    }

    suspend fun createJournal(journal: List<JournalRequest>): JournalResponse {
        return api.createJournal(tokenBearer, journal)
    }

    // Menambahkan metode untuk menghapus entri jurnal
    suspend fun deleteJournal(date: String) {
        // Panggil API untuk menghapus entri berdasarkan tanggal
        api.deleteJournal(tokenBearer, date)
    }
}

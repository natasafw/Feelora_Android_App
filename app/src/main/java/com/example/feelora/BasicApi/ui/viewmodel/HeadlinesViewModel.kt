package com.example.feelora.BasicApi.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feelora.BasicApi.data.model.Headlines
import com.example.feelora.BasicApi.data.model.NewsResponse
import com.example.feelora.BasicApi.data.repository.HeadlinesRepository
import com.example.feelora.BasicApi.utils.NetworkUtils
import com.example.feelora.BasicApi.utils.Resource
import kotlinx.coroutines.launch

class HeadlinesViewModel(private val repository: HeadlinesRepository) : ViewModel() {

    private val _data = MutableLiveData<Resource<List<Headlines>>>()
    val data: LiveData<Resource<List<Headlines>>> = _data

    fun getHeadlines(context: Context, forceRefresh: Boolean = false) {
        if (_data.value == null || forceRefresh) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                viewModelScope.launch {
                    try {
                        _data.value = Resource.Loading() // Emit loading state
                        val response: NewsResponse = repository.fetchHeadlines() // Ambil data dari repository
                        val articles = response.articles // Ambil daftar artikel dari response

                        if (articles.isEmpty()) { // Periksa apakah daftar artikel kosong
                            _data.postValue(Resource.Empty("No Articles Found")) // Emit empty state
                        } else {
                            _data.postValue(Resource.Success(articles)) // Emit success state
                        }
                    } catch (e: Exception) {
                        Log.e("HeadlinesViewModel", "Error: ${e.message}")
                        _data.postValue(Resource.Error("Unknown Error: ${e.message}")) // Emit error state
                    }
                }
            } else {
                _data.postValue(Resource.Error("No Internet Connection")) // Emit error jika tidak ada koneksi
            }
        }
    }
}

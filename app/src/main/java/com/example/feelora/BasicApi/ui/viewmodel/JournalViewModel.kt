package com.example.gulaku.basic_api.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feelora.BasicApi.data.model.JournalRequest
import com.example.feelora.BasicApi.data.model.JournalResponse
import com.example.feelora.BasicApi.data.repository.JournalRepository
import com.example.feelora.BasicApi.utils.NetworkUtils
import com.example.feelora.BasicApi.utils.Resource
import kotlinx.coroutines.launch

class JournalViewModel(private val repository: JournalRepository) : ViewModel() {
    // Data di-set sebagai LiveData agar UI dapat melakukan observe status saat mengambil data
    private val _data = MutableLiveData<Resource<JournalResponse>>()
    val data: LiveData<Resource<JournalResponse>> = _data

    // createStatus di-set sebagai LiveData agar UI dapat melakukan observe status saat mengirim data
    private val _createStatus = MutableLiveData<Resource<Unit>>()
    val createStatus: LiveData<Resource<Unit>> = _createStatus

    // deleteStatus di-set sebagai LiveData agar UI dapat melakukan observe status saat menghapus data
    private val _deleteStatus = MutableLiveData<Resource<Unit>>()
    val deleteStatus: LiveData<Resource<Unit>> = _deleteStatus

    // Fungsi untuk mengambil entri jurnal
    fun getJournalEntries(context: Context, forceRefresh: Boolean = false) {
        if (_data.value == null || forceRefresh) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                viewModelScope.launch {
                    try {
                        _data.value = Resource.Loading()
                        val response = repository.fetchJournal()
                        if (response.items.isEmpty()) {
                            _data.postValue(Resource.Empty("No journal entries found"))
                        } else {
                            _data.postValue(Resource.Success(response))
                        }
                    } catch (e: Exception) {
                        _data.postValue(Resource.Error("Unknown error: ${e.message}"))
                    }
                }
            } else {
                _data.postValue(Resource.Error("No internet connection"))
            }
        }
    }

    // Fungsi untuk membuat entri jurnal
    fun createJournalEntry(context: Context, journalEntry: JournalRequest) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            viewModelScope.launch {
                try {
                    _createStatus.value = Resource.Loading()

                    // Mengirimkan entri jurnal sebagai list
                    repository.createJournal(listOf(journalEntry))
                    _createStatus.postValue(Resource.Success(Unit))

                    // Refresh data setelah create sukses
                    getJournalEntries(context, forceRefresh = true)

                } catch (e: Exception) {
                    _createStatus.postValue(Resource.Error("Unknown error: ${e.message}"))
                }
            }
        } else {
            _createStatus.postValue(Resource.Error("No internet connection"))
        }
    }

    fun deleteJournalEntry(context: Context, uuid: String) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            viewModelScope.launch {
                try {
                    _deleteStatus.value = Resource.Loading()
                    repository.deleteJournal(uuid) // Pastikan ini memanggil UUID yang benar
                    _deleteStatus.postValue(Resource.Success(Unit))
                } catch (e: Exception) {
                    _deleteStatus.postValue(Resource.Error("Unknown error: ${e.message}"))
                }
            }
        } else {
            _deleteStatus.postValue(Resource.Error("No internet connection"))
        }
    }

}

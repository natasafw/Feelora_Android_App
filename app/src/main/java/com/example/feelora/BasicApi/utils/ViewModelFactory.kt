package com.example.feelora.BasicApi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feelora.BasicApi.ui.viewmodel.HeadlinesViewModel
import com.example.gulaku.basic_api.ui.viewmodel.JournalViewModel

class ViewModelFactory<T : ViewModel>(
    private val viewModelClass: Class<T>,
    private val creator: () -> T
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModelClass)) {
            @Suppress("UNCHECKED_CAST")
            return creator() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


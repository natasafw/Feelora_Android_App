package com.example.feelora.BasicApi.data.model

data class JournalResponse(
    val cursor: String,
    val items: List<JournalItems>,
    val next_page: String,
)

data class JournalItems(
    val _created: Double,
    val _data_type: String,
    val _is_deleted: Boolean,
    val _modified: Double,
    val _self_link: String,
    val _user: String,
    val _uuid: String,
    val date: String,
    val title: String,
    val journal: String,
    val imageUrl: String = "https://img.freepik.com/free-vector/emoji-concept-illustration_114360-28073.jpg?uid=R101470331&ga=GA1.1.1600669425.1683111917&semt=ais_hybrid"
)

package com.marcosanjos.app_agenda.service

import com.marcosanjos.app_agenda.model.Item
import retrofit2.http.GET

interface ApiService {

    @GET("items")
    suspend fun getItems(): List<Item>

}

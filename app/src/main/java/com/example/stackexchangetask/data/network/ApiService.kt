package com.example.stackexchangetask.data.network

import com.example.stackexchangetask.data.dto.QuestionsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("questions")
    suspend fun getQuestions(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String,
    ): QuestionsDTO

    @GET("search/advanced")
    suspend fun searchQuestions(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("tagged") tagged: String,
        @Query("title") title: String,
        @Query("site") site: String,
    ): QuestionsDTO
}
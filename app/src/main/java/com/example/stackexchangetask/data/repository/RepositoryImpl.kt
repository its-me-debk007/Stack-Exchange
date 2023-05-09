package com.example.stackexchangetask.data.repository

import com.example.stackexchangetask.data.network.ApiService
import com.example.stackexchangetask.domain.mapper.toQuestionModels
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getQuestions(
        order: String,
        sort: String,
        site: String
    ): List<QuestionModel> = apiService.getQuestions(order, sort, site).toQuestionModels()

    override suspend fun searchQuestions(
        order: String,
        sort: String,
        tagged: String,
        title: String,
        site: String
    ): List<QuestionModel> = apiService.searchQuestions(order, sort, tagged, title, site).toQuestionModels()


}
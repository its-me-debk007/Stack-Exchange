package com.example.stackexchangetask.domain.repository

import com.example.stackexchangetask.domain.model.QuestionModel

interface Repository {

    suspend fun getQuestions(
        order: String = "desc",
        sort: String = "hot",
        site: String = "stackoverflow",
    ): List<QuestionModel>

    suspend fun searchQuestions(
        order: String = "desc",
        sort: String = "activity",
        tagged: String,
        title: String,
        site: String = "stackoverflow",
    ): List<QuestionModel>
}
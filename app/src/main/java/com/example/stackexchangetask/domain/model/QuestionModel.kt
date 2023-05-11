package com.example.stackexchangetask.domain.model

data class QuestionModel(
    val answer_count: Int,
    val view_count: Int,
    val creation_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: OwnerModel,
    val question_id: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
)

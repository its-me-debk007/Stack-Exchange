package com.example.stackexchangetask.domain.mapper

import com.example.stackexchangetask.data.dto.QuestionsDTO
import com.example.stackexchangetask.domain.model.OwnerModel
import com.example.stackexchangetask.domain.model.QuestionModel

fun QuestionsDTO.toQuestionModels(): List<QuestionModel> {
    val models = mutableListOf<QuestionModel>()

    this.items.forEach {
        models.add(
            QuestionModel(
                it.answer_count,
                it.creation_date,
                it.last_edit_date,
                it.link,
                OwnerModel(it.owner.display_name, it.owner.link, it.owner.profile_image),
                it.question_id,
                it.score,
                it.tags,
                it.title
            )
        )
    }

    return models
}
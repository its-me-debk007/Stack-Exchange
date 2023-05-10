package com.example.stackexchangetask.presentation.controller

import android.content.Context
import coil.load
import com.airbnb.epoxy.EpoxyController
import com.example.stackexchangetask.R
import com.example.stackexchangetask.databinding.ModelAdBinding
import com.example.stackexchangetask.databinding.ModelQuestionBinding
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.util.ViewBindingKotlinModel
import com.example.stackexchangetask.util.addChip

class HomeEpoxyController(private val context: Context, private val data: List<QuestionModel>) :
    EpoxyController() {

    override fun buildModels() {

        data.forEachIndexed { index, questionModel ->
            if ((index + 1) % 5 == 0) {
                AdEpoxyModel("https://wallpaperaccess.com/full/494838.jpg")
                    .id(index)
                    .addTo(this)
            }

            QuestionEpoxyModel(
                questionModel.title,
                questionModel.answer_count,
                questionModel.view_count,
                questionModel.score,
                questionModel.creation_date,
                questionModel.owner.display_name,
                questionModel.owner.profile_image,
                questionModel.tags,
                context
            ).id(questionModel.question_id).addTo(this)
        }
    }
}

data class QuestionEpoxyModel(
    val _question: String,
    val _answers: Int,
    val views: Int,
    val _votes: Int,
    val _askTime: Int,
    val _ownerName: String,
    val _ownerImage: String,
    val tags: List<String>,
    val context: Context
) : ViewBindingKotlinModel<ModelQuestionBinding>(R.layout.model_question) {

    override fun ModelQuestionBinding.bind() {

        ansViews.text = "$_answers answers  $views views"
        votes.text = "$_votes votes"
        question.text = _question
        askTime.text = "asked $_askTime hours ago"
        ownerName.text = _ownerName
        ownerImage.load(_ownerImage)
        tags.forEach {
            tagsChipGrp.addChip(context, it)
        }
    }
}

data class AdEpoxyModel(val adLink: String) :
    ViewBindingKotlinModel<ModelAdBinding>(R.layout.model_ad) {

    override fun ModelAdBinding.bind() {

    }
}
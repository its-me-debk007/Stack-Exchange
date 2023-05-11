package com.example.stackexchangetask.presentation.controller

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.example.stackexchangetask.R
import com.example.stackexchangetask.databinding.ModelAdBinding
import com.example.stackexchangetask.databinding.ModelQuestionBinding
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.util.AD_LINK
import com.example.stackexchangetask.util.ViewBindingKotlinModel
import com.example.stackexchangetask.util.addChip
import java.util.Date

class HomeEpoxyController(
    private val context: Context,
    private var data: List<QuestionModel>,
    private val listener: ClickListener
) : EpoxyController() {

    override fun buildModels() {

        data.forEachIndexed { index, questionModel ->
            if ((index + 1) % 5 == 0) {
                AdEpoxyModel(context)
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
                questionModel.link,
                questionModel.owner.link,
                context,
                listener
            ).id(questionModel.question_id)
                .addTo(this)
        }
    }

    fun updateData(newData: List<QuestionModel>) {
        data = newData
        requestModelBuild()
    }
}

data class QuestionEpoxyModel(
    val _question: String,
    val _answers: Int,
    val views: Int,
    val _votes: Int,
    val _askTime: Int,
    val _ownerName: String,
    val _ownerImage: String?,
    val tags: List<String>,
    val link: String,
    val ownerProfile: String?,
    val context: Context,
    val listener: ClickListener
) : ViewBindingKotlinModel<ModelQuestionBinding>(R.layout.model_question) {

    override fun ModelQuestionBinding.bind() {

        container.setOnClickListener {
            listener.onLinkClick(link)
        }

        ownerName.setOnClickListener {
            ownerProfile?.let { profile -> listener.onLinkClick(profile) }
        }

        ansViews.text = "$_answers answers  $views views"

        votes.text = "$_votes votes"

        question.text = _question

        val date = Date(_askTime.toLong() * 1000).toString()
        askTime.text = "asked on ${date.substring(4, 10)} at ${date.substring(12, 16)}"

        ownerName.text = _ownerName

        Glide.with(context)
            .load(_ownerImage)
            .into(ownerImage)

        tags.forEach {
            tagsChipGrp.addChip(context, it)
        }
    }
}

data class AdEpoxyModel(val context: Context) :
    ViewBindingKotlinModel<ModelAdBinding>(R.layout.model_ad) {

    override fun ModelAdBinding.bind() {
        Glide.with(context)
            .load(AD_LINK)
            .into(image)
    }

}

interface ClickListener {
    fun onLinkClick(link: String)
}
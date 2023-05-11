package com.example.stackexchangetask.domain.usecase

import com.example.stackexchangetask.util.ApiState
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchQuestionsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(title: String, tagged: String): Flow<ApiState<List<QuestionModel>>> = flow {

        emit(ApiState.Loading())

        try {

            emit(
                ApiState.Success(
                    data = repository.searchQuestions(
                        title = title,
                        tagged = tagged
                    )
                )
            )

        } catch (e: Exception) {
            emit(ApiState.Error(msg = e.message.toString()))
        }
    }
}
package com.example.stackexchangetask.domain.usecase

import com.example.stackexchangetask.common.ApiState
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<ApiState<List<QuestionModel>>> = flow {

        emit(ApiState.Loading())

        try {

            emit(ApiState.Success(data = repository.getQuestions()))

        } catch (e: Exception) {
            emit(ApiState.Error(msg = e.message.toString()))
        }
    }
}
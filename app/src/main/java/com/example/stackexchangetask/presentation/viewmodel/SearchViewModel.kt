package com.example.stackexchangetask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackexchangetask.util.ApiState
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.domain.usecase.SearchQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchQuestionsUseCase: SearchQuestionsUseCase) :
    ViewModel() {

    private val _questions = MutableStateFlow<ApiState<List<QuestionModel>>>(ApiState.Loading())
    val questions: StateFlow<ApiState<List<QuestionModel>>> get() = _questions

    fun searchQuestions(title: String, tagged: String) {
        searchQuestionsUseCase(title, tagged).onEach {
            when (it) {
                is ApiState.Loading -> {
                    _questions.value = ApiState.Loading()
                }

                is ApiState.Success -> {
                    _questions.value = ApiState.Success(data = it.data)
                    Log.d("VIEWMODEL", it.data.toString())
                }

                is ApiState.Error -> {
                    _questions.value = ApiState.Error(msg = it.errorMsg.toString())
                    Log.d("VIEWMODEL", it.errorMsg.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
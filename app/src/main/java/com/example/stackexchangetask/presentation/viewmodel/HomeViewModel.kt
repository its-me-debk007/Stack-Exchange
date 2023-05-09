package com.example.stackexchangetask.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stackexchangetask.common.ApiState
import com.example.stackexchangetask.domain.model.QuestionModel
import com.example.stackexchangetask.domain.usecase.GetQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getQuestionsUseCase: GetQuestionsUseCase): ViewModel() {

    private val _questions = MutableStateFlow<ApiState<List<QuestionModel>>>(ApiState.Loading())
    val questions: StateFlow<ApiState<List<QuestionModel>>> get() = _questions

    init {
        getQuestions()
    }

    @Inject @ApplicationContext lateinit var context: Context

    private fun getQuestions() {
        getQuestionsUseCase().onEach {
            when (it) {
                is ApiState.Loading -> {
                    _questions.value = ApiState.Loading()
                }
                is ApiState.Success -> {
                    _questions.value = ApiState.Success(data = it.data)
                    Log.d("VIEWMODEL", it.data.toString())
                    Toast.makeText(context, it.data.toString(), Toast.LENGTH_LONG).show()
                }
                is ApiState.Error -> {
                    _questions.value = ApiState.Error(msg = it.errorMsg.toString())
                    Log.d("VIEWMODEL", it.errorMsg.toString())
                    Toast.makeText(context, it.errorMsg.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }.launchIn(viewModelScope)
    }
}
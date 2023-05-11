package com.example.stackexchangetask.data.dto

data class QuestionsDTO(
    val has_more: Boolean,
    val items: List<ItemDTO>,
    val quota_max: Int,
    val quota_remaining: Int
)
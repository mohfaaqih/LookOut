package com.example.sharedpref

data class TodoItem (
    val id: String,
    val title: String,
    val description: String? = null,
    val done_at: String? = null,
    val created_at: String
)
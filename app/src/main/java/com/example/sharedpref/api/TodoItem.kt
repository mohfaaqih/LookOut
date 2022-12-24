package com.example.sharedpref.api

data class TodoItem (
    val id: String,
    val username: String,
    val description: String,
    val done_at: String? = null,
    val created_at: String,
    val Session_id: String
)
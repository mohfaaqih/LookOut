package com.example.sharedpref

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TodoApi {
    @GET("/rest/v1/todo?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<TodoItem>>
}
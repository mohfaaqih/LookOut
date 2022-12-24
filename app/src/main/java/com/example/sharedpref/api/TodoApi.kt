package com.example.sharedpref.api

import retrofit2.Response
import retrofit2.http.*

public interface TodoApi {
    @GET("/rest/v1/item?select=*")
    suspend fun get(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String
    ) : Response<List<TodoItem>>

    @POST("/rest/v1/item")
    suspend fun create(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body todoData: TodoData
    )
    @DELETE("/rest/v1/item")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Query("id") idQuery : String
    ) : Response<Unit>
}
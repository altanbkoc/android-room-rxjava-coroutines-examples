package com.altankoc.retrofitwithcoroutines.service

import com.altankoc.retrofitwithcoroutines.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    //https://dummyjson.com/users
    @GET("users")
    suspend fun getData(): Response<List<User>>
}
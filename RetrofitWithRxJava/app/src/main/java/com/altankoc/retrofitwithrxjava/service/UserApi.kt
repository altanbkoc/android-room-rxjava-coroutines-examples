package com.altankoc.retrofitwithrxjava.service

import com.altankoc.retrofitwithrxjava.model.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface UserApi {

    //https://jsonplaceholder.typicode.com/users

    @GET("users")
    fun getData(): Observable<List<User>>
}
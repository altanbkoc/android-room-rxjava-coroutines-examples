package com.altankoc.retrofitwithcoroutines.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,


    @SerializedName("email")
    val email: String,

    @SerializedName("username")
    val usarname: String,

    @SerializedName("address")
    val address: Address

):Serializable

data class Address(
    @SerializedName("city")
    val city: String,

    @SerializedName("street")
    val street: String

):Serializable
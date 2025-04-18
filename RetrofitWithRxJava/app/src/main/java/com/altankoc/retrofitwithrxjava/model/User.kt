package com.altankoc.retrofitwithrxjava.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class User(
    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("address")
    val address: Address

):Serializable

data class Address(

    @SerializedName("street")
    val street: String,

    @SerializedName("city")
    val city: String
):Serializable
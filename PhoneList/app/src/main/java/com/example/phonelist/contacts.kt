package com.example.phonelist

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface contacts {
    @GET("api/")

    fun getResults(@Query("results") count: Int = 20): Call<RutItem>
}

data class RutItem(
    val results : List<Item>
)

data class Item(
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: FullName,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: FullNamePicture
)

data class FullName(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String
)

data class FullNamePicture(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
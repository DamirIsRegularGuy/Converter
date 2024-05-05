package com.example.converter

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetInformation {
    @GET("data.json")
    fun getData(): Call<List<Item>>
}

data class Item(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nominal")
    val nominal: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("value")
    val value: Double
)
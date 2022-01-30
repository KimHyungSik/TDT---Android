package com.todotracks.tdt.model

import com.google.gson.annotations.SerializedName

data class SubTopic(
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("latitude")
    val latitude: Long,
    @SerializedName("longitude")
    val longitude: Long,
    @SerializedName("main_no")
    val main_no: Int,
    @SerializedName("plan_dt")
    val plan_dt: String,
    @SerializedName("title")
    val title: String
)
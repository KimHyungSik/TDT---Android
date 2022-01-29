package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class SubTopicDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("is_complete")
    val is_complete: Boolean,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("plan_dt")
    val plan_dt: String,
    @SerializedName("sub_no")
    val sub_no: Int,
    @SerializedName("title")
    val title: String
)
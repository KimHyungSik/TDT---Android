package com.todotracks.tdt.model

import com.google.gson.annotations.SerializedName

data class MainTopic(
    @SerializedName("end_date")
    val end_date: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("title")
    val title: String
)
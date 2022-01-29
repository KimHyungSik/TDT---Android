package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class MainTopic(
    @SerializedName("data_list")
    val date_list: List<String>,
    @SerializedName("end_date")
    val end_date: String,
    @SerializedName("main_no")
    val main_no: Int,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("title")
    val title: String
)
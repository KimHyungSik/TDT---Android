package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class MainTopics(
    @SerializedName("main_topics")
    val main_topics: List<MainTopic>
)
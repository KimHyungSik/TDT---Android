package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class SubTopicsDto(
    @SerializedName("complete_rate")
    val complete_rate: Int,
    @SerializedName("sub_topics")
    val sub_topics: List<SubTopicDto>
)
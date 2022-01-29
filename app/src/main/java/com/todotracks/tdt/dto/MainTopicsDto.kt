package com.todotracks.tdt.dto

import com.google.gson.annotations.SerializedName

data class MainTopicsDto(
    @SerializedName("main_topics")
    val main_topicDtos: List<MainTopicDto>
)
package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopics
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TopicListApi {
    @GET("/api/travel/main-topics")
    @Headers("X-MEMBER-NO: 1")
    suspend fun getMainTopics() : MainTopics
}
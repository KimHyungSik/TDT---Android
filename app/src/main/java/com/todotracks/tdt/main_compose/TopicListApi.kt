package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopicsDto
import com.todotracks.tdt.model.MainTopic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TopicListApi {
    @GET("/api/travel/main-topics")
    suspend fun getMainTopics() : MainTopicsDto

    @POST("/api/travel/main-topics")
    suspend fun addMainTopics(@Body mainTopic: MainTopic) : Response<Unit>
}
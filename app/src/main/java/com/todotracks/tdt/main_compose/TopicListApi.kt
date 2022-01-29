package com.todotracks.tdt.main_compose

import com.todotracks.tdt.dto.MainTopicsDto
import com.todotracks.tdt.dto.SubTopicsDto
import com.todotracks.tdt.model.MainTopic
import com.todotracks.tdt.model.SubTopic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TopicListApi {
    @GET("/api/travel/main-topics")
    suspend fun getMainTopics() : MainTopicsDto

    @POST("/api/travel/main-topics")
    suspend fun addMainTopics(@Body mainTopic: MainTopic) : Response<Unit>

    @GET("/api/travel/sub-topics")
    suspend fun getSubTopics(@Query("main_no") main_no: Int, @Query("date") date : String): SubTopicsDto

    @POST("/api/travel/sub-topics")
    suspend fun addSubTopics(@Body subTopic: SubTopic) : Response<Unit>
}
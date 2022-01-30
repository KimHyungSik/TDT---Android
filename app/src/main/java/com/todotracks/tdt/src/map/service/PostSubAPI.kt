package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.src.map.model.PostSubRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PostSubAPI {
    @POST("/api/travel/sub-topics")
    fun postSub(@Body params: PostSubRequest): Call<Unit>
}
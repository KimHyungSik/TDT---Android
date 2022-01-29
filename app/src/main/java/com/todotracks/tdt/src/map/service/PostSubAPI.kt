package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.src.map.model.PostSubRequest
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import retrofit2.Call
import retrofit2.http.*

interface PostSubAPI {
    @GET("/api/travel/sub-topics")
    fun postSub(@Body params: PostSubRequest): Call<Int>
}
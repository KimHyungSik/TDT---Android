package com.todotracks.tdt.src.check_map.service

import com.todotracks.tdt.src.map.model.SubTopicCheck
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface PutCheckAPI {
    @PUT("/api/travel/sub-topics/{subNo}/status")
    fun postSub(@Path("subNo") subNo: Int, @Body subTopicCheck : SubTopicCheck): Call<Unit>
}
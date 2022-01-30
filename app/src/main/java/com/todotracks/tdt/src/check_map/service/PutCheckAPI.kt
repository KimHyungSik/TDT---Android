package com.todotracks.tdt.src.check_map.service

import com.todotracks.tdt.src.map.model.PostSubRequest
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import retrofit2.Call
import retrofit2.http.*

interface PutCheckAPI {
    @PUT("/api/travel/sub-topics/{subNo}/status")
    fun postSub(@Path("subNo") subNo: Int): Call<String?>
}
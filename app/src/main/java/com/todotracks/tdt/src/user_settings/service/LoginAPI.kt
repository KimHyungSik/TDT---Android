package com.todotracks.tdt.src.user_settings.service

import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginAPI {
    @POST("/api/auth/login")
    fun getUserId(@Body params: loginRequest): Call<loginResponse>
}
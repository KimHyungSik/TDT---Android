package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse

interface PostSubView{
    fun onPostSubSuccess(response: Int)
    fun onPostSubFailure(message: String)
}
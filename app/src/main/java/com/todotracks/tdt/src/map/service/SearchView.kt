package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse

interface SearchView{
    fun onGetSearchSuccess(response: SearchResponse)
    fun onGetSearchFailure(message: String)
}
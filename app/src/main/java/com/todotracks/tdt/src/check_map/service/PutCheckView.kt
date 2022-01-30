package com.todotracks.tdt.src.check_map.service

import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse

interface PutCheckView{
    fun onPutCheckSuccess(response: String?)
    fun onPutCheckFailure(message: String)
}
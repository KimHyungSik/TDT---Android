package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.service.LoginAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val view: SearchView) {
    fun tryGetSearch(search: String){
        val searchAPI = ApplicationClass.sRetrofit.create(SearchAPI::class.java)
        searchAPI.getSearch(search).enqueue(object :
            Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                view.onGetSearchSuccess(response.body() as SearchResponse)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                view.onGetSearchFailure(t.message ?: "통신 오류")
            }
        })
    }
}
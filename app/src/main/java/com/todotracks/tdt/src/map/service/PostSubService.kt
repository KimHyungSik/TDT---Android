package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.map.model.PostSubRequest
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.service.LoginAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostSubService(val view: PostSubView) {
    fun tryPostSub(postSubRequest: PostSubRequest){
        val postsubAPI = ApplicationClass.sRetrofit.create(PostSubAPI::class.java)
        postsubAPI.postSub(postSubRequest).enqueue(object :
            Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                view.onPostSubSuccess(response.body() as Int)
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                view.onPostSubFailure(t.message ?: "통신 오류")
            }
        })
    }
}
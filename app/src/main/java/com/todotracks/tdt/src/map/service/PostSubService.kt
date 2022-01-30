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
            Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                view.onPostSubSuccess(response.body())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                view.onPostSubFailure(t.message ?: "통신 오류")
            }
        })
    }
}
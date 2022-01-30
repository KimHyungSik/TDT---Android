package com.todotracks.tdt.src.map.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.map.model.PostSubRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostSubService(val view: PostSubView) {
    fun tryPostSub(postSubRequest: PostSubRequest){
        val postsubAPI = ApplicationClass.sRetrofit.create(PostSubAPI::class.java)
        postsubAPI.postSub(postSubRequest).enqueue(object :
            Callback<Unit> {

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                view.onPostSubFailure(t.message ?: "통신 오류")
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                view.onPostSubSuccess("")
            }
        })
    }
}
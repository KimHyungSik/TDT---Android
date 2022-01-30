package com.todotracks.tdt.src.check_map.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.map.model.PostSubRequest
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.map.service.PostSubAPI
import com.todotracks.tdt.src.map.service.PostSubView
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.service.LoginAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutCheckService(val view: PutCheckView) {
    fun tryPutCheck(subNo: Int){
        val putcheckAPI = ApplicationClass.sRetrofit.create(PutCheckAPI::class.java)
        putcheckAPI.postSub(subNo).enqueue(object :
            Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                view.onPutCheckSuccess(response.body())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                view.onPutCheckFailure(t.message ?: "통신 오류")
            }
        })
    }
}
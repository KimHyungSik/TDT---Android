package com.todotracks.tdt.src.check_map.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.map.model.SubTopicCheck
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutCheckService(val view: PutCheckView) {
    fun tryPutCheck(subNo: Int, subTopicCheck: SubTopicCheck){
        val putcheckAPI = ApplicationClass.sRetrofit.create(PutCheckAPI::class.java)
        putcheckAPI.postSub(subNo, subTopicCheck).enqueue(object :
            Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                view.onPutCheckSuccess(Unit)
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                view.onPutCheckFailure(Unit)
            }
        })
    }
}
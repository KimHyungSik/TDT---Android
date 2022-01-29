package com.todotracks.tdt.src.user_settings.service

import com.todotracks.tdt.kotlin.config.ApplicationClass
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginView) {
    fun tryPostLogin(loginRequest: loginRequest){
        val loginAPI = ApplicationClass.sRetrofit.create(LoginAPI::class.java)
        loginAPI.getUserId(loginRequest).enqueue(object :
            Callback<loginResponse> {
            override fun onResponse(call: Call<loginResponse>, response: Response<loginResponse>) {
                view.onPostLoginSuccess(response.body() as loginResponse)
            }

            override fun onFailure(call: Call<loginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}
package com.todotracks.tdt.src.user_settings.service

import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse

interface LoginView{
    fun onPostLoginSuccess(response: loginResponse)
    fun onPostLoginFailure(message: String)
}
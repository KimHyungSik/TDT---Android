package com.todotracks.tdt.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import com.todotracks.tdt.databinding.ActivitySplashBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.user_settings.UserSettingActivity
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.service.LoginService
import com.todotracks.tdt.src.user_settings.service.LoginView

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),
    LoginView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var token_id =  Settings.Secure.getString(
            applicationContext.contentResolver, Settings.Secure.ANDROID_ID
        )

        var loginReq = loginRequest(token_id.toString())
        LoginService(this).tryPostLogin(loginReq)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UserSettingActivity::class.java))
            finish()
        }, 1500)
    }

    override fun onPostLoginSuccess(response: loginResponse) {
        var text = getSharedPreferences("tdt", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("X-ACCESS-TOKEN", response.member_no.toString())
        editor.commit()
    }

    override fun onPostLoginFailure(message: String) {
        TODO("Not yet implemented")
    }
}
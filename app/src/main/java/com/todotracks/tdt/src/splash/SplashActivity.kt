package com.todotracks.tdt.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import com.todotracks.tdt.MainActivity
import com.todotracks.tdt.databinding.ActivitySplashBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.map.MapActivity
import com.todotracks.tdt.src.user_settings.model.loginRequest
import com.todotracks.tdt.src.user_settings.model.loginResponse
import com.todotracks.tdt.src.user_settings.my_user_Id
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
            startActivity(Intent(this, MapActivity::class.java))
            finish()
        }, 1500)
    }

    override fun onPostLoginSuccess(response: loginResponse) {
        var userId = response.member_no
        my_user_Id(userId)
        var text = getSharedPreferences("tdt", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("X-MEMBER-NO", userId.toString())
        editor.commit()
        var intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    override fun onPostLoginFailure(message: String) {
        TODO("Not yet implemented")
    }
}
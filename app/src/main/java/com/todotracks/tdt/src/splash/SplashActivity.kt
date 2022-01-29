package com.todotracks.tdt.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.todotracks.tdt.databinding.ActivitySplashBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.user_settings.UserSettingActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UserSettingActivity::class.java))
            finish()
        }, 1500)
    }
}